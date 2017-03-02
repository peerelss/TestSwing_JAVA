package anno;

/**
 * Created by root on 17-3-2.
 */
@TestAnnotation(count = 2)
public class TestAnno {
    public static void main(String[] args){
        TestAnnotation annotation=TestAnno.class.getAnnotation(TestAnnotation.class);
        System.out.println(annotation.count());
    }
}
