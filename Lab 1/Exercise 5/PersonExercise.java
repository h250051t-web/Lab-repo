
class Address {
    String city;

    // EXPLAIN: constructor stores the city in the Address object
    Address(String city) {
        this.city = city;
    }
}

class Person {
    String name;
    Address address;

    // EXPLAIN: a Person can hold a reference to an Address object
    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}

public class PersonExercise {
    public static void main(String[] args) {

        // EXPLAIN: create an Address object for Harare
        Address address1 = new Address("Harare");

        // EXPLAIN: the Person object stores a reference to address1
        Person person1 = new Person("Tawanda", address1);

        // EXPLAIN: chained dots access the city through person -> address -> city
        System.out.println("City: " + person1.address.city);

        // EXPLAIN: this Person has no Address object
        Person person2 = new Person("Rudo", null);

        // EXPLAIN: check for null before accessing a field through the reference
        if (person2.address == null) {
            System.out.println(person2.name + " has no address.");
        } else {
            System.out.println("City: " + person2.address.city);
        }
    }
}
