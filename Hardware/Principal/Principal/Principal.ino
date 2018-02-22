#include "I2Cdev.h"  // Inclui a biblioteca I2C (Protocolo de comunicação usado pelo MPU6050)

#include "MPU6050_6Axis_MotionApps20.h" // Inclui a biblioteca MPU6050_6Axis_MotionApps20 (Configuração do modo escolhido para setup no MPU6050)

// --- Mapeamento de Hardware ---
/* Rotary encoder read example */
#define ENC_A 8
#define ENC_B 9
#define ENC_PORT PINB

#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE //Implementa se necessário uma comunicação para o arduino com o protocolo I2C incluindo-se a biblioteca Wire
    #include "Wire.h" // Inclui biblioteca Wire (Comunicação do arduino com o protocolo I2C)
#endif //Fim da macro condição 


#define LOG_INPUT 1 // Define a constante LOG_INPUT, se for diferente de 0 imprime no serial os valores obtidos pelo giroscópio; se for 0 não imprime 

// MPU


MPU6050 mpu; //Inclui as funções do MPU6050

// MPU controle/status das variáveis
bool dmpReady = false;  // Verifica se a conexão com o DMP (Digital Motion Processor) no MPU6050 foi realizada com sucesso, se sim recebe True
uint8_t mpuIntStatus;   // Mantém o byte atual do estado de interrupção do MPU6050
uint8_t devStatus;      // Retorno após cada operação do dispositivo com a comunicação I2Cdev (se for igual a 0 sucesso, se for diferente de 0 erro)
uint16_t packetSize;    // Tamanho do pacote DMP esperado (o padrão é 42 bytes)
uint16_t fifoCount;     // Contagem de todos os bytes atualmente no FIFO (acrônimo para First In, First Out, que em português significa primeiro a entrar, primeiro a sair) 
uint8_t fifoBuffer[64]; // Buffer de armazenamento do FIFO

// Orientação/Movimento das variáveis 
Quaternion q;           // [w, x, y, z]         Recipiente quaternion
VectorFloat gravity;    // [x, y, z]            Vetor gravidade
float ypr[3];           // [yaw, pitch, roll]   yaw/pitch/roll Recipiente e vetor gravidade (ypr[3] é igual a 3 pois usamos o valor de roll)

//Strings constantes 
String tipoDado = "$STS";
String separador = ",";

// Temporizadores


long time1Hz = 0; // Define time1Hz como sendo do tipo long 


volatile bool mpuInterrupt = false;     // indica se o pino de interrupção do MPU6050 foi alto (HIGH)
void dmpDataReady() // Define a função dmpDataReady para verificação de inicialização do DMP (Digital Motion Processor)
{
    mpuInterrupt = true; // Análisa se houve interrupção do MPU6050 se sim recebe True 
}


void setup() //Função de configuração do setup para inicialização no arduino
{
    /* Setup encoder pins as inputs */
    pinMode(ENC_A, INPUT);
    digitalWrite(ENC_A, HIGH);
    pinMode(ENC_B, INPUT);
    digitalWrite(ENC_B, HIGH);
    //Rotary Encoder
    
    // aderir ao barramento I2C (a biblioteca I2Cdev não faz isso automaticamente)
    #if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE  // Verifica se a implementação da comunicação I2Cdev com o arduino é compatível com as funções I2CDEV_ARDUINO_WIRE
        Wire.begin(); // Inicia a comunicação com o MPU6050 e o arduino
        TWBR = 24; // Define a velocidade do Clock de comunicação do MPU e o CPU do arduino
    #elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE //Se não, verifica se a implementação da comunicação I2Cdev com o arduino é compatível com as funções I2CDEV_BUILTIN_FASTWIRE
        Fastwire::setup(400, true); // Inicia a função de comunicação do MPU com o arduino pela biblioteca Fastwire definindo os paramêtros da função
    #endif // Encerra a Macro condição 

    //Inicia a porta de comunicação Serial 
    Serial.begin(115200);

    // Inicia o dispostivo de comunicação I2C (MPU6050)
    Serial.println(F("Initializing I2C devices...")); // Imprime no Serial, inicialização dos dispositivos I2C
    mpu.initialize(); // Função de inicialização do MPU6050

    // Verifica a conexão do dispositivo com o arduino
    Serial.println(F("Testing device connections...")); // Imprime no serial, Teste de conexão com o dispositivo
    Serial.println(mpu.testConnection() ? F("MPU6050 connection successful") : F("MPU6050 connection failed")); //Realiza a função de teste de conexão com o MPU e imprime se falhou ou funcionou  

    // Carrega e configura o DMP (Digital Motion Processor)
    Serial.println(F("Initializing DMP...")); // Imprime na tela, inicialização do DMP
    devStatus = mpu.dmpInitialize(); // Invoca a função de inicialização do DMP e salva seu retorno na variável devStatus 

    // Forneça os valores de deslocamentos do giroscópio, ajustados para a sensibilidade mínima
    mpu.setXGyroOffset(220); // Define o valor como paramêtro da função para o ângulo X do Giroscópio
    mpu.setYGyroOffset(76); // Define o valor como paramêtro da função para o ângulo Y do Giroscópio
    mpu.setZGyroOffset(-85); // Define o valor como paramêtro da função para o ângulo Y do Giroscópio
    mpu.setZAccelOffset(1788); // Define o valor como paramêtro da função para o ângulo Z do Acelerômetro 

    // Certifica-se se o valor da inicialização do DMP foi realizada com sucesso (retorna 0 se assim for)
    if (devStatus == 0)
    {
        // Liga o DMP, agora que está ok (Passou no teste)
        Serial.println(F("Enabling DMP...")); // Imprime no Serial, Habilitando o DMP
        mpu.setDMPEnabled(true); // Invoca a função de habilitação do DMP passando o paramêtro verdadeiro

        // Ativa a detecção de interrupção do Arduino
        Serial.println(F("Enabling interrupt detection (Arduino external interrupt 0)...")); // Imprime no Serial, Habilitação de detecção de interrupação (Externo ao arduino interrupção 0)
        attachInterrupt(0, dmpDataReady, RISING); // Invoca a função de interrupção do MPU6050 com os paramêtros fornecidos entre parênteses 
        mpuIntStatus = mpu.getIntStatus(); // Invoca a função do status do funcionamento da interrupção do MPU6050 e salva o retorno na variável mpuIntStatus 

        // define nosso sinalizador DMP Ready para que a função loop () principal saiba que está tudo ok
        Serial.println(F("DMP ready! Waiting for first interrupt...")); // Imprime no Serial que o DMP está ok e aguardando interrupções do MPU6050
        dmpReady = true; // A variável dmpReady recebe o valor bolleano Verdadeiro

        // obter tamanho de pacote DMP esperado para comparação posterior
        packetSize = mpu.dmpGetFIFOPacketSize(); 
    }
    else // Se a inicialização do DMP falhou realiza os códigos abaixo 
    {
        // ERRO!
        Serial.print(F("DMP Initialization failed (code ")); // Imprime no Serial, inicialização do DMP falhou (código da falha)
        Serial.print(devStatus); // Imprime no Serial, o código da falha presente na variável devStatus
        Serial.println(F(")")); // Imprime no Serial, ") 
    }
}


void loop()  //Função de configuração do loop de repetição do arduino
{
    static unsigned char counter = 0; //this variable will be changed by encoder input
    char tmpdata;
    /**/
    tmpdata = read_encoder();
    counter += tmpdata;
    
    // se a programação de configuração falhou, não realiza nada do loop
    if (!dmpReady) return;

    // aguarde interrupção do MPU com pacote(s) extra disponível
    while (!mpuInterrupt && fifoCount < packetSize)
    {   
        unsigned long currentMillis = millis(); // Define currentMillis como sendo do tipo unsigned long, que recebe os milisegundos de cada loop 
        
        if (currentMillis - time1Hz >= 1000) // Se o tempo de currentMillis menos o tempo de time1Hz for menor ou igual a 1000 realiza o código abaixo 
        {
            loopAt1Hz(); // Invoca a função loopAt1Hz que está definida próxima ao final do código 
            time1Hz = currentMillis; // Define que a variável time1Hz é igual a variável currentMillis (De 1000 em 1000 "A cada segundo")
        }
    }

    // redefini o sinalizador de interrupção e obtém o valor do byte INT_STATUS
    mpuInterrupt = false; // A variável mpuInterrupt recebe Falso para redefinição  
    mpuIntStatus = mpu.getIntStatus(); // A variável mpuIntStatus recebe o valor do estado do byte de interrupção do MPU6050

    // Obter conta FIFO atual
    fifoCount = mpu.getFIFOCount();

    // Verifica se ocorre Overflow "estouro da memória" (Não deve acontecer com frêquencia)
    if ((mpuIntStatus & 0x10) || fifoCount == 1024)
    {
        // Se acontecer o overflow o FIFO do MPU6050 é resetado para continuar limpo
        mpu.resetFIFO(); // Função que reseta o FIFO (acrônimo para First In, First Out, que em português significa primeiro a entrar, primeiro a sair) do MPU6050
        Serial.println(F("FIFO overflow!")); // Imprime no Serial, que aconteceu overflow no FIFO
    }
    
    // Caso contrário, verifica se há interrupção pronta de dados do DMP (isso deve acontecer com freqüência)
    else if (mpuIntStatus & 0x02)
    {
        // Aguarda o comprimento correto dos dados disponíveis, deve ser uma espera MUITO curta
        while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();

        // Ler um pacote de FIFO
        mpu.getFIFOBytes(fifoBuffer, packetSize);
        
        // Pista FIFO contagem aqui caso haja mais que 1 pacote disponível
        // (Isso nos permite ler imediatamente mais sem esperar por uma interrupção de novos dados)
        fifoCount -= packetSize;

        mpu.dmpGetQuaternion(&q, fifoBuffer); // Lê os valores do recepiente quartenion do MPU6050 obtidos pelo DMP e com uma ordem FIFO 
        mpu.dmpGetGravity(&gravity, &q); // Lê os valores da gravidade do MPU6050 obtidos pelo DMP e com uma ordem FIFO 
        mpu.dmpGetYawPitchRoll(ypr, &q, &gravity); // Lê os valores de Yaw, Pitch e Roll do MPU6050 obtidos pelo DMP e com uma ordem FIFO 
        #if LOG_INPUT // Se a constante LOG_INPUT da Macro decisão for diferente de 0 imprime no Serial os valores de Yaw, Pitch e Roll obtidos pelo MPU6050, Se for igual a 0 não imprime 
            Serial.print(tipoDado);
            Serial.print(separador);
            //Serial.print("Giroscópio: "); // Imprime no Serial, "ypr" (Que significa Yaw, Pitch e Roll, respectivamente do MPU6050)
            //Serial.print(ypr[0] * 180/M_PI); // Imprime no Serial, o valor de Yaw 
            //Serial.print("\t"); // Imprime um espaçamento de tabulação (tab)
            Serial.print(ypr[1] * 180/M_PI); // Imprime no Serial, o valor de Pitch
            Serial.print(separador);
            //Serial.print("\t\t"); // Imprime um espaçamento de tabulação (tab)
            //Serial.print(ypr[2] * 180/M_PI); // Imprime no Serial, o valor de Roll
            //Serial.print("Rotary Encoder: ");
            Serial.println(counter, DEC);
        #endif // Encerra a Macro condição
   }
}

/* returns change in encoder state (-1,0,1) */
char read_encoder()
{
  static char enc_states[] = {0,-1,1,0,1,0,0,-1,-1,0,0,1,0,1,-1,0};
  static unsigned char old_AB = 0;
  /**/
  old_AB <<= 2;                   //remember previous state
  old_AB |= ( ENC_PORT & 0x03 );  //add current state
  return ( enc_states[( old_AB & 0x0f )]);
}

void loopAt1Hz() // Define a função loopAt1Hz (É realizada a cada segundo)
{
}
