/*
 * Yeh program hostel management system ka ek simulation hai. Yeh students ke eligibility,
 * payment status aur notification bhejne ka kaam karta hai filters ke zariye.
 */

package pipeline2; 

import java.util.ArrayList;
import java.util.List;

// MODEL LAYER (Student ka class model layer mein hota hai)
/*
 * Student class ka kaam student ke details store karna hai, jaise name, age aur payment status.
 */
class Student {
    String name; // Student ka naam
    int age; // Student ki age
    boolean hasPaid; // Kya student ne fees pay ki hai? (true/false)

    // Constructor jo student ka data initialize karta hai
    public Student(String name, int age, boolean hasPaid) {
        this.name = name;
        this.age = age;
        this.hasPaid = hasPaid;
    }
}

// SERVICE LAYER (Student ko check karne aur process karne ka kaam yahan hota hai)
/*
 * Filter ek interface hai jo ek method `apply` define karta hai.
 * Yeh method student ka data leta hai aur check karta hai.
 */
interface Filter {
    boolean apply(Student student);
}

/*
 * EligibilityFilter student ki age check karta hai.
 * Agar student 18 ya usse zyada ka hai, to woh eligible hota hai.
 */
class EligibilityFilter implements Filter {
    @Override
    public boolean apply(Student student) {
        if (student.age >= 18) {
            System.out.println(student.name + " is eligible.");
            return true; // Filter pass ho gaya
        }
        System.out.println(student.name + " isn't eligible.");
        return false; // Filter fail ho gaya
    }
}

/*
 * PaymentFilter check karta hai kya student ne fees pay ki hai.
 * Agar `hasPaid` true hai to filter pass ho jata hai.
 */
class PaymentFilter implements Filter {
    @Override
    public boolean apply(Student student) {
        if (student.hasPaid) {
            System.out.println(student.name + " Has paid his Hostel fee.");
            return true; // Filter pass ho gaya
        }
        System.out.println(student.name + " Hasn't paid his Hostel fee.");
        return false; // Filter fail ho gaya
    }
}

/*
 * ServiceMailingFilter student ko notification bhejne ka kaam karta hai.
 * Yeh hamesha pass hota hai kyunki iska kaam sirf notification bhejna hai.
 */
class ServiceMailingFilter implements Filter {
    @Override
    public boolean apply(Student student) {
        System.out.println("Service notification " + student.name + " :Avail your Service Dear Student");
        return true; // Filter hamesha pass hota hai
    }
}

/*
 * FilterPipeline ek system hai jo multiple filters ko line mein process karta hai.
 * Agar ek bhi filter fail ho jaye to process wahin stop ho jata hai.
 */
class FilterPipeline {
    private final List<Filter> filters = new ArrayList<>(); // Filters ka list

    // Filter pipeline mein add karne ka method
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /*
     * Student ko har filter ke through process karta hai.
     * Agar koi filter fail ho jaye to process stop ho jata hai.
     */
    public void process(Student student) {
        for (Filter filter : filters) {
            if (!filter.apply(student)) break; // Agar filter fail kare to process stop karo
        }
    }
}

// CONTROLLER LAYER
/*
 * HostelSystem class program ka entry point hai.
 * Yeh students create karta hai, pipeline setup karta hai aur unko process karta hai.
 */
public class HostelSystem {
    public static void main(String[] args) {
        // Students create kar rahe hain
        Student student1 = new Student("Ali", 20, true); // Eligible aur fees pay ki hui
        Student student2 = new Student("Sara", 16, false); // Not eligible aur fees pay nahi ki

        // Filter pipeline create kar rahe hain aur filters add kar rahe hain
        FilterPipeline pipeline = new FilterPipeline();
        pipeline.addFilter(new EligibilityFilter());       // Age eligibility check karega
        pipeline.addFilter(new PaymentFilter());           // Payment status check karega
        pipeline.addFilter(new ServiceMailingFilter());    // Notification bhejega

        // Pehle student ko process kar rahe hain
        System.out.println("Processing Student 1:");
        pipeline.process(student1);

        // Dusre student ko process kar rahe hain
        System.out.println("\nProcessing Student 2:");
        pipeline.process(student2);
    }
}
