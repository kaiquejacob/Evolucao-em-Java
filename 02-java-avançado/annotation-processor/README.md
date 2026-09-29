# Annotation Processing e Metaprogramação

## Anotações em Java
Anotações são metadados adicionados ao código-fonte que não alteram diretamente a lógica de execução. Elas são definidas com a sintaxe `@interface` e configuradas via meta-anotações:

* `@Retention`: Define até quando a anotação permanece disponível:
  * `SOURCE`: Descartada pelo compilador (usada para geração de código ou checagens do compilador).
  * `CLASS`: Gravada no arquivo `.class`, mas não disponível em runtime (padrão).
  * `RUNTIME`: Mantida na JVM e acessível via Reflection durante a execução.
* `@Target`: Define onde a anotação pode ser aplicada (`TYPE`, `FIELD`, `METHOD`, `PARAMETER`, etc.).

```java
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Builder {
}

```

## Processamento em Tempo de Compilação

O **Annotation Processing API** (JSR 269) permite ao compilador do Java (`javac`) interceptar anotações e gerar novos arquivos de código-fonte (`.java`) **antes** de concluir o processo de compilação.

Diferente de Reflection, que atua em tempo de execução (*runtime*), o Annotation Processor atua em *build-time*, garantindo custo zero de performance durante a execução da aplicação.

## Implementação de um Processor

Para criar um gerador de código, estende-se a classe `AbstractProcessor`:

```java
@SupportedAnnotationTypes("br.com.dio.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Builder.class)) {
            // Analisa a estrutura da classe e gera código fonte adicional via Filer API
        }
        return true;
    }
}

```

## A Pipelining do Compilador

1. O `javac` lê os arquivos-fonte.
2. Os Processors registrados são executados em rodadas (*rounds*).
3. Se um processor gerar novos arquivos `.java`, uma nova rodada é iniciada para processá-los.
4. A compilação final consolida todo o código gerado em bytecode (`.class`).

A pegadinha principal é que um Annotation Processor **não pode modificar arquivos de código existentes** (com exceção de hacks internos como o Lombok); ele pode apenas **gerar novos arquivos** complementares.

