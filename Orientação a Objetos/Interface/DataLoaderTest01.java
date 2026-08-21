public class DataLoaderTest01 {
    static void main(String[] args) {
        DataBaseLoader dataBaseLoader = new DataBaseLoader();
        FileLoader fileLoader = new FileLoader();

        dataBaseLoader.carregar();
        fileLoader.carregar();

        System.out.println();

        dataBaseLoader.remove();
        fileLoader.remove();

        System.out.println();

        dataBaseLoader.checkPermission();
        fileLoader.checkPermission();

        System.out.println();

        DataLoader.retriveMaxDataSize();
        DataBaseLoader.retriveMaxDataSize();
    }
}
