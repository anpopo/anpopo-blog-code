package constructor.packageprivate.concrete;

public class DependencyStart {

    private Child1 child1;
    private Child2 child2;
    private Child3 child3;

    DependencyStart(Child1 child1, Child2 child2, Child3 child3) {
        this.child1 = child1;
        this.child2 = child2;
        this.child3 = child3;
    }

    public void printDependency() {
        System.out.println("child1 : " + child1.toString());
        System.out.println("child2 : " + child2.toString());
        System.out.println("child3 : " + child3.toString());
    }
}
