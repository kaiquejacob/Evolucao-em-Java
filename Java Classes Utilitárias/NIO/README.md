# NIO (New I/O)

## Path e Paths x File
`Path` (junto com a fábrica `Paths.get(...)`) é a evolução moderna da antiga classe `File`, com uma API mais rica pra manipular caminhos: navegar entre diretórios, comparar caminhos, resolver caminhos relativos, sem misturar isso com operações de leitura/escrita (que ficam a cargo da classe `Files`).

```java
Path caminho = Paths.get("pasta", "arquivo.txt");
```

## normalize, resolve e relativize
`normalize()` limpa um caminho removendo referências redundantes tipo `.` e `..`. `resolve()` combina um caminho base com um caminho relativo, produzindo o caminho completo. `relativize()` faz o inverso: calcula o caminho relativo necessário para ir de um caminho até outro.

```java
Path base = Paths.get("/home/usuario");
Path completo = base.resolve("documentos/arquivo.txt");
Path relativo = base.relativize(completo); // "documentos/arquivo.txt"
```

## Files (operações de leitura/escrita)
A classe `Files` concentra as operações práticas sobre arquivos que antes exigiam várias linhas com `File`/`FileReader`: ler todas as linhas de uma vez, copiar, mover, deletar, checar existência — de forma mais direta.

```java
List<String> linhas = Files.readAllLines(caminho);
```

## Atributos de Arquivo
`BasicFileAttributes` fornece metadados comuns a qualquer sistema operacional (tamanho, datas de criação/modificação). `DosFileAttributes` e `PosixFileAttributes` são extensões específicas de Windows e Unix/Linux respectivamente, trazendo atributos exclusivos de cada sistema (ex: permissões estilo Unix no Posix).

```java
BasicFileAttributes attrs = Files.readAttributes(caminho, BasicFileAttributes.class);
attrs.size();
attrs.creationTime();
```

## Navegação de Diretórios: DirectoryStream e SimpleFileVisitor
`DirectoryStream` percorre os arquivos de **um único nível** de um diretório. `SimpleFileVisitor`, usado com `Files.walkFileTree()`, percorre uma árvore de diretórios **recursivamente**, chamando métodos de callback para cada arquivo e pasta visitados — útil pra operações que precisam processar subpastas também.

## PathMatcher
Permite filtrar caminhos usando padrões glob (`*.java`, `**/*.txt`) em vez de regex completo, mais prático para filtrar arquivos por extensão ou padrão de nome.

```java
PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.java");
matcher.matches(caminho.getFileName());
```

## ZipOutputStream
Permite criar arquivos `.zip` programaticamente, escrevendo cada entrada (arquivo) dentro do zip através de um `ZipEntry`.
