import java.util.Scanner;

public class Main {
    private static String[]Roman={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    private static String[]Roman10={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};//для того чтобы преобразовать результат в римские цифры
    public static void main(String[] args)throws Exception{
        Scanner sc=new Scanner(System.in);//считывание с консоли
        System.out.println("Введите 2 арабских или римских числа и знак операции через пробел");
        System.out.println(calc(sc.nextLine()));//прочитанную с клав строку передаем в метод calc,а результат выводим на экран
    } //цифры и знак вводятся через пробел

    private static int getRoman(String input){//преобразование строки, записанной римскими цифрами в число
        for (int i = 0; i < Roman.length; i++) {//проходим по массиву римских цифр, чтобы найти соотв цифру римского разряда
            if(Roman[i].equals(input))//если мы нашли такую цифру
                return i+1;//возвращаем порядковый номер этой цифры начиная с 1
        }
        return-1;//не нашли цифру
    }
    private static String getRoman(int input)throws Exception {//преобразование числа в римские цифры
        if (input<=0){
            throw new Exception();
        }
        String ret=Roman10[input/10];//получаем старший разряд римских цифр
        if(input%10!=0){ //если число не делится на 10
            ret=ret+Roman[input%10-1];//дописываем в конец младший разряд
        }
        return ret;//возвращаем результат
    }
    public static String calc(String input) throws Exception{// основной метод
        String[]s=input.trim().split(" ");//разделяем полученную строку на элементы массива через пробел
        if (s.length!=3){  //проверяем длину массива
            throw new Exception();  //исключения
        }
        if(s[1].length()!=1){ //проверяем что средний элемент это один символ
            throw new Exception();
        }
        boolean Arabian=false;//не считаем цифру Арабской
        int num1=getRoman(s[0]);//первое число пытаемя прочитать как римское
        if(num1<0){
            Arabian=true;
            num1=Integer.parseInt(s[0]);//если не удалось прочитать как римское читаем как арабское
        }

        int num2= Arabian?Integer.parseInt(s[2]):getRoman(s[2]);//если первое число Арабское то и второе читаем как Арабское иначе второе читаем как римское
        if (num2<=0&&!Arabian){
            throw new Exception();
        }
        if(num1<1||num1>10||num2<1||num2>10){
            throw new Exception();
        }
        int result=-1;
        switch(s[1].charAt(0)){ //берем второй элемент массива строк(знак) и первый символ этой строки
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num1-num2;
                break;
            case '*':
                result=num1*num2;
                break;
            case '/':
                result=num1/num2;
                break;
            default:throw new Exception();
        }
        if(Arabian)return Integer.toString(result);//возврат значения из метода
        else return getRoman(result);

    }
}

