package io.chbtian.effectivejava3rd.bio.decorator;

public class DecoratorDemo3 {
    public static void main(String[] args) {
        Phone mi = new XiaoMi();
        mi = new Bluetooth(mi);
        mi = new Camera(mi);
        System.out.println(mi.sendMessage());
        System.out.println(mi.call());


        Phone mate = new HuaweiMate();
        mate = new Bluetooth(mate);
        mate = new Camera(mate);
        System.out.println(mate.sendMessage());
        System.out.println(mate.call());
    }
}

interface Phone{
    String  sendMessage();
    String call();
}
class XiaoMi implements Phone{

    @Override
    public String sendMessage() {
        return "mi: "+"发送短信！";
    }

    @Override
    public String call() {
        return  "mi: "+ "打电话！";
    }
}
class HuaweiMate implements Phone{
    @Override
    public String sendMessage() {
       return  "mate: "+ "发送短信！";
    }

    @Override
    public String call() {
       return  "mate: "+ "打电话！";
    }
}

abstract class PhoneDecorator implements Phone{
    protected Phone phone;
    protected void install(){
        System.out.println("安装app!");
    }
}

class Bluetooth extends PhoneDecorator{

    public Bluetooth(Phone phone) {
        this.phone = phone;
    }

    public String sendFiles(){
       return " + 面对面发送文件！";
    }
    @Override
    public String sendMessage() {
       return this.phone.sendMessage()+this.sendFiles();
    }

    @Override
    public String call() {
        return this.phone.call();
    }
}

class Camera extends PhoneDecorator{

    public Camera(Phone phone) {
        this.phone = phone;
    }

    public String videoChat(){
        return " + 视频通话";
    }
    @Override
    public String sendMessage() {
        return this.phone.sendMessage();
    }

    @Override
    public String call() {
        return this.phone.call() + this.videoChat();
    }
}