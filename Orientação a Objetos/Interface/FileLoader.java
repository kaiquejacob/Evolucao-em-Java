public class FileLoader implements DataLoader, DataRemover{
    @Override
    public void carregar() {
        System.out.println("Carregando dados do arquivo");
    }

    @Override
    public void remove() {
        System.out.println("Deletando dados de um arquivo");
    }

    @Override
    public void checkPermission() {
        System.out.println("Checando permissões do arquivo");
    }
}
