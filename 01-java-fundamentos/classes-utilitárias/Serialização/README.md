# Serialização

## O que é Serialização
Serializar é transformar o estado de um objeto Java numa sequência de bytes, permitindo salvar esse objeto em disco ou enviá-lo pela rede, e depois reconstruí-lo (desserializar) exatamente como estava. A classe precisa implementar a interface marcadora `Serializable` (sem métodos para implementar, só sinaliza que a classe pode ser serializada).

```java
public class Aluno implements Serializable {
    private String nome;
    private int idade;
}
```

## Gravando e Lendo com ObjectOutputStream/ObjectInputStream
`ObjectOutputStream` escreve o objeto serializado num destino (geralmente um arquivo). `ObjectInputStream` faz o caminho inverso, reconstruindo o objeto a partir dos bytes salvos.

```java
try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("aluno.dat"))) {
    out.writeObject(aluno);
}

try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("aluno.dat"))) {
    Aluno lido = (Aluno) in.readObject();
}
```

## serialVersionUID
É um identificador de versão da classe usado para garantir compatibilidade entre a versão da classe que serializou o objeto e a versão que está tentando desserializar. Se não for declarado, o Java gera um automaticamente baseado na estrutura da classe — e qualquer mudança estrutural (adicionar/remover atributo) muda esse valor gerado, podendo quebrar a leitura de objetos salvos com a versão antiga (`InvalidClassException`). Declarar manualmente dá mais controle sobre quando a compatibilidade deve quebrar de propósito.

```java
private static final long serialVersionUID = 1L;
```

## O Modificador transient
Um atributo marcado como `transient` é **ignorado** na serialização — ao desserializar, ele volta com o valor padrão do tipo (`null`, `0`, etc.), não o valor que tinha antes de salvar. Usado para dados sensíveis (senha) ou que não fazem sentido persistir (uma conexão de rede, um cache temporário).

```java
private transient String senha; // nunca é gravado no arquivo serializado
```
