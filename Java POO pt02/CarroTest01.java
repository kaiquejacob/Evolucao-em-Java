public class CarroTest01 {
    static void main(String[] args) {
        Carro.setVelocidadeLimite(180);

        Carro c1 = new Carro("BMW", 280);
        Carro c2 = new Carro("Mercedes", 275);
        Carro c3 = new Carro("Audi", 290);


        c1.imprimi();
        c2.imprimi();
        c3.imprimi();

    }
}
