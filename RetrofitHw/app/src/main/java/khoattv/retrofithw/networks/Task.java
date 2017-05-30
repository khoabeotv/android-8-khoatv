package khoattv.retrofithw.networks;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class Task {
  private String due_date;
  private boolean done;
  private String id;
  private double payment_per_hour;
  private String name;
  private String color;
  private String local_id;

  public Task(String due_date, boolean done, String id, double payment_per_hour, String name, String color, String local_id) {
    this.due_date = due_date;
    this.done = done;
    this.id = id;
    this.payment_per_hour = payment_per_hour;
    this.name = name;
    this.color = color;
    this.local_id = local_id;
  }

  public String getDue_date() {
    return due_date;
  }

  public boolean isDone() {
    return done;
  }

  public String getId() {
    return id;
  }

  public double getPayment_per_hour() {
    return payment_per_hour;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public String getLocal_id() {
    return local_id;
  }

  @Override
  public String toString() {
    return "Task{" +
            "due_date='" + due_date + '\'' +
            ", done=" + done +
            ", id='" + id + '\'' +
            ", payment_per_hour=" + payment_per_hour +
            ", name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", local_id='" + local_id + '\'' +
            '}';
  }
}
