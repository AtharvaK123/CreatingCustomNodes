public class SuperList<E> {
    ListNode<E> root, end;
    int size = 0;

    public SuperList() {
        root = null;
        end = null;
    }

    public boolean add(E val) {
        ListNode<E> newNode = new ListNode<>(val);
        if (root == null) {
            root = newNode;
            end = root;
        } else {
            end.setNext(newNode);
            newNode.setPrevious(end);
            end = newNode;
        }
        size++;
        return true;
    }

    public boolean add(int index, E val) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        if (index == size) {
            return add(val);
        } else if (index == 0) {
            return push(val);
        } else {
            ListNode<E> newNode = new ListNode<>(val);
            ListNode<E> current = root;

            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            ListNode<E> prev = current.getPrevious();
            prev.setNext(newNode);
            newNode.setPrevious(prev);
            newNode.setNext(current);
            current.setPrevious(newNode);

            size++;
            return true;
        }
    }

    public boolean push(E val) {
        ListNode<E> newNode = new ListNode<>(val);
        if (root == null) {
            root = newNode;
            end = root;
        } else {
            root.setPrevious(newNode);
            newNode.setNext(root);
            root = newNode;
        }
        size++;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        ListNode<E> current = root;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        ListNode<E> prev = current.getPrevious();
        ListNode<E> next = current.getNext();

        if (prev != null) {
            prev.setNext(next);
        } else {
            root = next;
        }

        if (next != null) {
            next.setPrevious(prev);
        } else {
            end = prev;
        }

        size--;
        return current.getValue();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListNode<E> current = root;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public class ListNode<E> {
        private E val;
        private ListNode<E> next, previous;

        public ListNode(E val) {
            this.val = val;
        }

        public E getValue() {
            return val;
        }

        public void setPrevious(ListNode<E> value) {
            previous = value;
        }

        public void setNext(ListNode<E> value) {
            next = value;
        }

        public ListNode<E> getPrevious() {
            return previous;
        }

        public ListNode<E> getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrevious() {
            return previous != null;
        }
    }

    public static void main(String[] args) {
        SuperList<Integer> sL = new SuperList<>();
        sL.add(10