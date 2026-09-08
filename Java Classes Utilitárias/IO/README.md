# IO (API clássica de arquivos)

## File
A classe `File` representa um **caminho** no sistema de arquivos (e seus metadados: existe, é diretório, tamanho), mas não o conteúdo do arquivo em si. É usada para checar existência, criar, deletar ou listar arquivos/diretórios.

```java
File file = new File("arquivo.txt");
boolean criado = file.createNewFile();
```

## FileReader e FileWriter
Fazem leitura e escrita de arquivos **caractere por caractere**, de forma direta e sem buffer — cada chamada de leitura/escrita pode significar uma operação real de disco, o que é lento quando repetido muitas vezes.

```java
FileWriter writer = new FileWriter("saida.txt");
writer.write("Olá mundo");
writer.close();
```

## BufferedWriter e BufferedReader
"Envolvem" um `FileWriter`/`FileReader`, acumulando dados num buffer em memória antes de gravar (ou lendo um bloco maior de uma vez), reduzindo drasticamente o número de acessos reais ao disco. Na prática, quase sempre se usa a versão *Buffered* por cima da versão crua.

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("saida.txt"))) {
    bw.write("Linha 1");
    bw.newLine();
    bw.write("Linha 2");
}
```

`BufferedReader` também oferece `readLine()`, que lê uma linha inteira de uma vez — algo que `FileReader` sozinho não tem, já que ele só lê caractere a caractere.
