package io.chbtian.effectivejava3rd.bio.decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        Component com = new DecoratorTwo( new DecoratorOne(new ConcreteComponent()));
        com.operation();
    }
}

interface  Component {
    void operation();
}

class ConcreteComponent implements Component{

    @Override
    public void operation() {
        System.out.println("function");
    }
}

abstract class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}

class DecoratorOne extends Decorator{

    public DecoratorOne(Component component) {
        super(component);
    }


    @Override
    public void operation() {
        super.operation();
        System.out.println("function one");
    }
}

class DecoratorTwo extends Decorator{

    public DecoratorTwo(Component component) {
        super(component);
    }


    @Override
    public void operation() {
        super.operation();
        System.out.println("function two");
    }
}