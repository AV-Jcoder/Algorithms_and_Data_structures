package Data_Structures.Heaps;

/**
 * Реализация кучи на базе двоичного дерева.
 */
class HeapTree {

    private Node root;
    private int count;

    HeapTree(){
        this.root = null;
        this.count = 0;
    }
//********************************************************************************
    // Вставка в дерево.O(log*n)
    void insert(int key) {
        count++;
        if (root==null){
            root = new Node(key);
            return;
        } else {
            // Берем каунт делим на 2 сохраняем остаток от деления куда нибудь пока
            // результат деления не будет равен 1
            int result = count;
            StringBuffer path = new StringBuffer();
            while (result>1){
                path.append(result%2);
                result/=2;
            }
            path.reverse();

            Node parent = root;
            Node current = root;
            boolean isLeftChild = true;

            // Идём по пути до пустой ноды для вставки
            int index = 0;
            while (current!=null){
                parent = current;

                if (path.charAt(index++)=='0'){
                    current=current.leftChild;
                    isLeftChild=true;
                } else {
                    current=current.rightChild;
                    isLeftChild=false;
                }
            }

            // Добавляем новую ноду в левого или правого потомка. Если потомок левый.
            Node entering;
            if (isLeftChild){
                parent.leftChild=new Node(key);
                entering = parent.leftChild;
                entering.parent = parent;
            } else { // Иначе потомок правый
                parent.rightChild = new Node(key);
                entering = parent.rightChild;
                entering.parent = parent;
            }

            // Затем протягиваем вверх добавленный узел, на своё место.
            int temp = entering.data;
            while (entering.parent!=null && temp > entering.parent.data){
                entering.data = entering.parent.data;
                entering = entering.parent;
            }
            entering.data = temp;
        }
    } // Конец вставки
//********************************************************************************
    // Удаление корневого узла
    int delete() {
        if (root.leftChild==null && root.rightChild==null){
            int tmp = root.data;
            root = null;
            count--;
            return tmp;
        }
        int returnData = root.data;
        // Для удаления находим нижнюю правую ноду и перемещаем на место корня,
        // Затем просеиваем вниз.

        // Ищем путь до правой нижней ноды,
        // Преобразуя номер крайней ноды в двоичный код.
        int result = count;
        StringBuffer path = new StringBuffer();
        while (result>1) {
            path.append(result%2);
            result/=2;
        }
        path.reverse();

        // Идём по пути до крайней ноды, потом обнуляем ссылку у родителя нижней правой ноды,
        // значение ноды записываем в корень
        Node parent = root;
        Node current = root;
        boolean isLeftChild = true;
        int index = 0;
        while (index < path.length()) {
            parent = current;
            if (path.charAt(index++)=='0') { // Если 0, идём налево
                current=current.leftChild;
                isLeftChild=true;
            } else {                        // Иначе идём направо
                current=current.rightChild;
                isLeftChild=false;
            }
        }
        root.data = current.data; // Копируем в рут значение крайней ноды
        if (isLeftChild){
            parent.leftChild = null; // Обнуляем левую ссылку у родителя крайней ноды
        } else {
            parent.rightChild = null; // Или правую
        }
        count--;

        // Просеиваем новое значение корня вниз
        // Ищем ноду-потомка с максимальным значением и свапаем с текущей
        // Пока не окажемся ниже большего значения и выше меньшего.
        int tmp = root.data;
        current = root;
        Node nodeMaxChildData = getMaxChildData(root);
//        System.out.println(nodeMaxChildData.data);
        while(nodeMaxChildData!=null && tmp < nodeMaxChildData.data) {
            current.data = nodeMaxChildData.data;
            current = nodeMaxChildData;
            nodeMaxChildData = getMaxChildData(nodeMaxChildData);
        }
        current.data = tmp;
        return returnData;
    }

    // Вернуть ноду с наибольшим значением ключа
    private Node getMaxChildData(Node node) {
        if (node.rightChild!=null && node.leftChild!=null) {
            return node.leftChild.data > node.rightChild.data ? node.leftChild : node.rightChild;
        } else {
            return node.leftChild;
        }
    }

//********************************************************************************
    // Вывод на дисплей
    void display() {
        System.out.println("Root = ");
        System.out.println(root.toString());
    }

    private static class Node {
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private int data;

        private Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    " data=" + data +
                    " leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }
}
