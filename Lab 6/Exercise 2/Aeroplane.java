
class Aeroplane implements Flyable {

    // EXPLAIN: Aeroplane also implements Flyable,
    // but its flying behaviour is different.
    @Override
    public void fly() {
        System.out.println("Aeroplane flies using its engines.");
    }
}
