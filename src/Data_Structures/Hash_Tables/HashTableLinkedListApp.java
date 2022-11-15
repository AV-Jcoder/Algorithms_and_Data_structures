package Data_Structures.Hash_Tables;

public class HashTableLinkedListApp {
    public static void main(String[] args) {

        HashTableLinkedList table = new HashTableLinkedList(1000);
        for (int i = 0; i < 700; i++) {
            table.insert((int)(Math.random()*100_000));
        }

        table.showTable();

        table.delete(5);
        table.showTable();

    }
}
