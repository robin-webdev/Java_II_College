public class Member extends Person {
    public String plan;
    public int months;

    public Member(String name, int age, int id, String plan, int months) {
        super(name, age, id, plan, months);
        this.plan = plan;
        this.months = months;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" + "Name: " + name + " | Id: " + id + "\n" + "Age: " + age + " | Plan: " + plan + " | Months: " + months + "\n" + "----------------------------------------";
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
}