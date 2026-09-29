public interface DataLoader {
    public abstract void carregar();

    default void checkPermission(){
        System.out.println("Fazendo checagem de permissões");
    }

    public static void retriveMaxDataSize(){
        System.out.println("Dentro do retriveMaxDataSize no DataLoader");
    }
}
