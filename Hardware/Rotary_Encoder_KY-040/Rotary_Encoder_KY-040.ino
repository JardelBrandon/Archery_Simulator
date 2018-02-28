//Programa : Teste basico encoder Arduino
//Autor : Arduino e Cia

//Carrega a biblioteca do encoder
#include <RotaryEncoder.h>

//Pinos de ligacao do encoder
RotaryEncoder encoder(A2, A3);

//Variavel para o botao do encoder
int valor = 0;
int newPos = 0;

void setup()
{
  //pinMode(7, INPUT);
  Serial.begin(115200);
  Serial.println("Gire o encoder....");
}

void loop()
{
  /*Descomentar se pretende usar a função de botão do rotary encoder
  //Verifica se o botao do encoder foi pressionado
  valor = digitalRead(7);
  if (valor != 1)
  {
    Serial.println("Botao pressionado");
    while (digitalRead(7) == 0)
      delay(10);
  }
  */

  //Le as informacoes do encoder
  static int pos = 0;
  encoder.tick();
  int newPos = encoder.getPosition();
  //Se a posicao foi alterada, mostra o valor
  //no Serial Monitor
  if (pos != newPos) {
    Serial.println(newPos);
    pos = newPos;
  }
}
