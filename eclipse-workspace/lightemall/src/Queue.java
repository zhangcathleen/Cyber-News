import tester.Tester;

// Represents a mutable collection of items
interface ICollection<T> {
  // Is this collection empty?
  boolean isEmpty();
  // EFFECT: adds the item to the collection
  void add(T item);
  // Returns the first item of the collection
  // EFFECT: removes that first item
  T remove();
}

class Queue<T> implements ICollection<T> {
  Deque<T> contents;
  Queue() {
    this.contents = new Deque<T>();
  }
  public boolean isEmpty() {
    return this.contents.size() <= 0;
  }
  public T remove() {
    return this.contents.removeFromHead();
  }
  public void add(T item) {
    this.contents.addAtTail(item); // NOTE: Different from Stack!
  }
}

interface IPred<T> {

  // applies the IPred to the given T
  boolean apply(T t);
}

class IsABC implements IPred<String> {

  // checks to see if the given String is equal to "abc"
  public boolean apply(String t) {
    return t.equals("abc");
  }

}

abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  // makes the previous node this node in the Deque list
  void makePrev(ANode<T> node) {
    this.prev = node;
  }

  // makes the next node this node in the Deque list
  void makeNext(ANode<T> node) {
    this.next = node;
  }

  // counts the number of nodes in this list Deque
  abstract int sizeHelper();

  // deletes this node from the Deque list
  public void deleteYourself() {
    this.prev.makeNext(this.next);
    this.next.makePrev(this.prev);
  }

  // finds the node that passes the given predicate
  abstract ANode<T> findHelper(IPred<T> pred);

  // removes if it is a node
  abstract void removeIfNode();

}

class Node<T> extends ANode<T> {
  T data;

  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  Node(T data, ANode<T> n, ANode<T> p) {
    this.data = data;
    if (n == null) {
      throw new IllegalArgumentException("The next node is null.");
    }
    else if (p == null) {
      throw new IllegalArgumentException("The previous node is null.");
    }
    else {
      this.next = n;
      this.prev = p;
    }
    n.makePrev(this);
    p.makeNext(this);
  }

  // counts the number of nodes in this Deque list
  int sizeHelper() {
    return 1 + this.next.sizeHelper();
  }

  // returns the first item in this list of Node that passes pred or header
  ANode<T> findHelper(IPred<T> pred) {
    if (pred.apply(this.data)) {
      return this;
    }
    else {
      return this.next.findHelper(pred);
    }
  }

  // removes this if it is a node
  void removeIfNode() {
    this.deleteYourself();
  }
}

class Sentinel<T> extends ANode<T> {

  Sentinel() {
    this.next = this;
    this.prev = this;
  }

  // counts the number of nodes connected to this sentinel
  int size() {
    return this.next.sizeHelper();
  }

  // counts the number of nodes in this Deque list
  int sizeHelper() {
    return 0;
  }

  // inserts T at the head of the list
  void addAtHead(T value) {
    new Node<T>(value, this.next, this);
  }

  // inserts T at the end of the list
  void addAtTail(T value) {
    new Node<T>(value, this, this.prev);
  }

  // removes the first node from this non-empty Deque
  public T removeFromHead() {
    Node<T> temp = (Node<T>) this.next;
    this.next.deleteYourself();
    return temp.data;

  }

  // removes the last node from this non-empty Deque
  public T removeFromTail() {
    Node<T> temp = (Node<T>) this.prev;
    this.prev.deleteYourself();
    return temp.data;

  }

  // returns the first item in this list of Node that passes pred or header
  ANode<T> find(IPred<T> pred) {
    return this.next.findHelper(pred);
  }

  // returns the first item in this list of Node that passes pred or header
  ANode<T> findHelper(IPred<T> pred) {
    return this;
  }

  // removes this if it is a Node
  void removeIfNode() {
    // this is not a node therefore we do not want to remove it
  }

}

class Deque<T> {
  Sentinel<T> header;

  Deque() {
    this.header = new Sentinel<T>();
  }

  Deque(Sentinel<T> sent) {
    this.header = sent;
  }

  // counts the number of nodes in a list Deque
  int size() {
    return this.header.size();
  }

  // inserts T at the head of the list
  void addAtHead(T value) {
    header.addAtHead(value);
  }

  // inserts T at the end of the list
  void addAtTail(T value) {
    header.addAtTail(value);
  }

  // removes the first node from this Deque
  T removeFromHead() {
    if (this.size() == 0) {
      throw new RuntimeException("This list is empty! Boo");
    }
    else {
      return header.removeFromHead();
    }
  }

  // removes the last node from this Deque
  T removeFromTail() {
    if (this.size() == 0) {
      throw new RuntimeException("This list is empty! Boo");
    }
    else {
      return header.removeFromTail();
    }
  }

  // returns the first item in this list of Node that passes pred or header
  ANode<T> find(IPred<T> pred) {
    return this.header.find(pred);

  }

  // removes the given ANode if it is a node
  void removeNode(ANode<T> t) {
    t.removeIfNode();
  }
}

class ExamplesDeque {

  Sentinel<String> sentinel1 = new Sentinel<String>();
  Node<String> node1 = new Node<String>("abc", this.sentinel1, this.sentinel1);
  Node<String> node2 = new Node<String>("bcd", this.sentinel1, this.node1);
  Node<String> node3 = new Node<String>("cde", this.sentinel1, this.node2);
  Node<String> node4 = new Node<String>("def", this.sentinel1, this.node3);

  Sentinel<String> sentinel2 = new Sentinel<String>();
  Node<String> node5 = new Node<String>("zyd", this.sentinel2, this.sentinel2);
  Node<String> node6 = new Node<String>("mop", this.sentinel2, this.node5);
  Node<String> node7 = new Node<String>("but", this.sentinel2, this.node6);
  Node<String> node8 = new Node<String>("cat", this.sentinel2, this.node7);
  Node<String> node9 = new Node<String>("jym", this.sentinel2, this.node8);

  Sentinel<String> sentinel3 = new Sentinel<String>();
  Sentinel<String> sentinel4 = new Sentinel<String>();

  Sentinel<String> sentinel5 = new Sentinel<String>();
  Node<String> node10 = new Node<String>("lef", this.sentinel5, this.sentinel5);
  Node<String> node11 = new Node<String>("wyh", this.sentinel5, this.node10);
  Node<String> nod11e = new Node<String>("okj", this.sentinel5, this.node11);

  Sentinel<String> sentinel6 = new Sentinel<String>();
  Node<String> node12 = new Node<String>("klj", this.sentinel6, this.sentinel6);
  Node<String> node13 = new Node<String>("elj", this.sentinel6, this.node12);
  Node<String> node14 = new Node<String>("slk", this.sentinel6, this.node13);

  Sentinel<String> sentinel7 = new Sentinel<String>();
  Node<String> node15 = new Node<String>("abc", this.sentinel7, this.sentinel7);
  Node<String> node16 = new Node<String>("def", this.sentinel7, this.node15);

  Sentinel<String> sentinel8 = new Sentinel<String>();
  Node<String> node17 = new Node<String>("akl", this.sentinel8, this.sentinel8);
  Node<String> node18 = new Node<String>("lij", this.sentinel8, this.node17);
  
  Sentinel<String> sentinel9 = new Sentinel<String>();
  Node<String> node19 = new Node<String>("asd", this.sentinel9, this.sentinel9);
  Node<String> node20 = new Node<String>("efg", this.sentinel8, this.node19);
  
  Deque<String> deque1 = new Deque<String>();
  Deque<String> deque2 = new Deque<String>(this.sentinel1);
  Deque<String> deque3 = new Deque<String>(this.sentinel2);
  Deque<String> deque4 = new Deque<String>(this.sentinel3);
  Deque<String> deque5 = new Deque<String>(this.sentinel4);
  Deque<String> deque6 = new Deque<String>(this.sentinel5);
  Deque<String> deque7 = new Deque<String>(this.sentinel6);
  Deque<String> deque8 = new Deque<String>(this.sentinel7);
  Deque<String> deque9 = new Deque<String>(this.sentinel8);
  Deque<String> deque10 = new Deque<String>(this.sentinel9);

  void testSize(Tester t) {
    t.checkExpect(this.deque1.size(), 0);
    t.checkExpect(this.deque2.size(), 4);
    t.checkExpect(this.deque3.size(), 5);
  }

  void testSizeHelper(Tester t) {
    t.checkExpect(this.sentinel2.sizeHelper(), 0);
    t.checkExpect(this.node4.sizeHelper(), 1);
  }

  void testIllegalArgument(Tester t) {
    t.checkConstructorException(new IllegalArgumentException("The next node is null."), "Node",
        "cde", null, null);
    t.checkConstructorException(new IllegalArgumentException("The previous node is null."), "Node",
        "cde", this.node1, null);
  }

  void testMakePrev(Tester t) {
    Sentinel<Integer> senty = new Sentinel<Integer>();
    Node<Integer> sentyNode = new Node<Integer>(539);
    senty.makePrev(sentyNode);
    t.checkExpect(senty.prev, sentyNode);
  }

  void testMakeNext(Tester t) {
    Sentinel<Integer> senty = new Sentinel<Integer>();
    Node<Integer> sentyNode = new Node<Integer>(539);
    senty.makeNext(sentyNode);
    t.checkExpect(senty.next, sentyNode);
  }

  void testAddAtHead(Tester t) {
    this.deque4.addAtHead("poop");
    t.checkExpect(this.deque4.header.next,
        new Node<String>("poop", this.sentinel3, this.sentinel3));
    t.checkExpect(this.sentinel3.next, new Node<String>("poop", this.sentinel3, this.sentinel3));
  }

  void testAddAtTail(Tester t) {
    this.deque5.addAtHead("jbanana");
    t.checkExpect(this.deque5.header.prev,
        new Node<String>("jbanana", this.sentinel4, this.sentinel4));
    t.checkExpect(this.sentinel4.prev, new Node<String>("jbanana", this.sentinel4, this.sentinel4));
  }

  void testRemoveFromHead(Tester t) {
    t.checkExpect(this.deque6.removeFromHead(), "lef");
    t.checkExpect(this.sentinel5.removeFromHead(), "wyh");
  }

  void testRemoveFromTail(Tester t) {
    t.checkExpect(this.deque7.removeFromTail(), "slk");
    t.checkExpect(this.sentinel6.removeFromTail(), "elj");
  }

  void testFind(Tester t) {
    t.checkExpect(this.deque6.find(new IsABC()), this.sentinel5);
    t.checkExpect(this.deque1.find(new IsABC()), new Sentinel<String>());
    t.checkExpect(this.deque8.find(new IsABC()), this.node15);
  }

  void testFindHelper(Tester t) {
    t.checkExpect(this.sentinel7.find(new IsABC()), this.node15);
    t.checkExpect(this.sentinel5.find(new IsABC()), this.sentinel5);
    t.checkExpect(this.sentinel3.find(new IsABC()), this.sentinel3);
  }

  void testApply(Tester t) {
    t.checkExpect(new IsABC().apply("slk"), false);
    t.checkExpect(new IsABC().apply("abc"), true);
  }

  void testDeleteYourself(Tester t) {
    this.node17.deleteYourself();
    t.checkExpect(this.sentinel8.next, this.node18);
  }
  
  void testRemoveNode(Tester t) {
    this.deque10.removeNode(this.node19);
    t.checkExpect(this.deque10.header.next, this.node20);
    this.sentinel9.removeIfNode();
    t.checkExpect(this.deque10.header, this.sentinel9);
  }

}
