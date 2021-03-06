# **类和接口**  
## **规则15：最小化类及其成员的访问权限**  
* 对于顶级（非嵌套）类或接口（top-level class or interface）  
    只能设置两种访问级别：public，package-private（什么也不写即为此级别）；  
    public：公有，表示外界都可以访问，是对外公开的API；  
    package-private：包内私有，表示只有本包内才可以访问，不对外公开；
    * 1、能设置为package-private的尽量设置，因为public是对外公开的（对外公开的API），外界客户端可以访问，发生变更就会影响客户端使用，要考虑可维护性和兼容性等问题；
    而package-private不对外公开，只是包内使用，变更不会影响到客户端的使用。
    * 2、如果一个package-private的类只被一个类使用，则应该将其设置为使用类中的私有静态内部类，进一步降低可访问性，减小其可能影响的范围（使用范围）。这点与1比较，1更重要。
* 对于成员（属性 field，方法 method，内部类 nested class，内部接口 nested interface）  
    四种访问级别：  
    private：只有在本类中才可以访问。  
    package-private：只有在本类和包内才可以访问。
    protected：只有在本类、包内和子类中才可以访问（在子类内访问时需要注意三种方式：this.属性名、super.属性名、属性名；不可以使用 new 父类对象.属性名）。
    public：  都可以访问。
       
** 
****
## **规则16：在公共类中为属性设置访问方法而不要将其访问级别设置为公有**   

* 项目1
* 项目2