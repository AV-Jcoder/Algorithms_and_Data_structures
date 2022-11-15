package Data_Structures.Hash_Tables;

public class HashTableApp {
    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10);

        for (int i = 0; i < hashTable.SIZE-1; i++) {
            int data =(int) (Math.random()*100);
            hashTable.insert(data);
        }

//        hashTable.delete(11);

        hashTable.displayTable();




    }
}
