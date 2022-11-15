package Data_Structures.Tree_Binary_Search;

/**
 * Класс реализует простейшее бинарное дерево с методами
 * find(int key) - простейший поиск;
 * insert()
 * getMinimum()
 * delete()
 */
public class Tree {

    private Node root;
//**************************************************************************************************
    // Поиск записи по значению ключа
    public Node find(int key){
        Node current = root;
        while (current!=null){
            if (current.iData == key){
                return current;
            } else if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return null;
    }
//**************************************************************************************************
    // Вставка в дерево
    public void insert(int id, double fDouble){
        Node node = new Node(id,fDouble);
        if (root == null){
            root = node;
            return;
        }
        Node current = root;
        Node parent;

        while (current!=null){
            parent = current;
            if (id<current.iData){
                current = current.leftChild;
                if (current==null){
                    parent.leftChild=node;
                }
            } else {
                current = current.rightChild;
                if (current==null){
                    parent.rightChild=node;
                }
            }
        }
    }
//**************************************************************************************************
    // Удаление элемента
    public boolean delete(int key){
        if(root==null){
            return false;
        } else {
            Node current = root;
            Node parent = root;
            boolean isLeftChild = true;
            // Поиск ключа по дереву
            while (current.iData!=key){
                current=parent;
                if (current==null){
                    // Ничего не нашли
                    return false;
                } else if (key<current.iData){
                    current = current.leftChild;
                    isLeftChild = true;
                } else {
                    current = current.rightChild;
                    isLeftChild = false;
                }
            }// end of while, and current == key

            // Если удаляемый узел не имеет потомков
            if (current.leftChild==null && current.rightChild==null){
                if (current == root) { // Если узел корневой
                    root = null;
                } else  if (isLeftChild){ // Если потомок левый
                    parent.leftChild = null;
                } else {                // Иначе удалить правого потомка
                    parent.rightChild = null;
                }
            // Если у удаляемого узла есть только один потомок слева
            } else if (current.rightChild==null){ // Если у удаляемого узла потомок только слева
                if (current==root){ // Если удаляемый узел корневой
                    root = root.leftChild;
                } else {            // Иначе удаляемый узел не корневой
                    if (isLeftChild){   // Если удаляемый узел является левым потомком
                        parent.leftChild = current.leftChild;
                    } else {            // Иначе удаляемый узел является правым потомком
                        parent.rightChild =  current.leftChild;
                    }
                }
            // Если у удаляемого узла есть только один потомок справа
            } else if (current.leftChild==null){ // Если потомок только справа
                 if (current==root){ // Если удаляемый узел корневой
                     root = root.rightChild;
                 } else {           // Иначе удаляемый узел не корневой
                     if (isLeftChild){      // Если удаляепмый узел является левым потомком
                         parent.leftChild = current.rightChild;
                     } else {               // Иначе удаляемый узел является правыйм потомком
                         parent.rightChild = current.rightChild;
                     }
                 }
            // Если удаляемый узел имеет ДВУХ потомков!!! ТО...
            // Ищем приемника
            } else {
                Node successor = getSuccessor(current); // Нода наследник.
                if (current==root){ // Если удаляем корень
                    successor.leftChild = root.leftChild; // Чтобы левое поддерево не убежало на мусорку.
                    root = successor;
                } else if (isLeftChild){ // Если удаляем левого потомка
                    successor.leftChild = current.leftChild;
                    parent.leftChild = successor;
                } else {                // Иначе удаляем правого потомка
                    successor.leftChild = current.leftChild;
                    parent.rightChild = successor;
                }
            }
        }
        return true;
    }
//**************************************************************************************************
    // Поиск наследника. Служебный метод для удаления элементов.
    private Node getSuccessor(Node delNode){
        Node current = delNode.rightChild;
        Node successor = delNode;
        Node successorParent = delNode;

        while(current!=null){ // Поиск приемника
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        // Если приемник не прямой потомок удаляемого элемента
        if (successor!=delNode.rightChild){
            // Перестраиваем поддерево
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }


        return successor;
    }
//**************************************************************************************************
    // Вывод на дисплей дерева
    public String displayTree(){
        return root.toString();
    }

    // Симметричный обход дерева
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node root){
        if (root !=null){
            inOrder(root.leftChild);
            System.out.println(root.iData);
            inOrder(root.rightChild);
        }
    }
//**************************************************************************************************
    // Поиск минимума
    public Node getMinimum(){
        if (root == null){
            return null;
        }
        Node current = root;
        while (current.leftChild!=null){
            current = current.leftChild;
        }
        return current;
    }
//**************************************************************************************************
    // Поиск максимума
    public Node getMaximum(){
        if (root == null){
            return null;
        } else {
            Node current = root;
            while(current.rightChild!=null){
                current = current.rightChild;
            }
            return current;
        }
    }
}