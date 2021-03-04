package by.epam.carsharing.entity.car;

import by.epam.carsharing.entity.Identifiable;

import java.math.BigDecimal;
import java.time.Year;

public class Car implements Identifiable {
    private int id;
    private String brand;
    private String model;
    private String color;
    private Integer mileage;
    private GearboxType gearbox;
    private Year manufacturedYear;
    private EngineType engineType;
    private BigDecimal pricePerDay;
    private String vin;
    private CarClass carClass;
    private String imagePath;

    public Car(int id, String brand, String model, String color, int mileage, GearboxType gearboxType, Year year, EngineType engineType, BigDecimal pricePerDay, String vin, String plate, CarClass carClass, String imagePath) {}

    public Car(int id, String brand, String model, String color, Integer mileage, GearboxType gearbox,
               Year manufacturedYear, EngineType engineType, BigDecimal pricePerDay, String vin, CarClass carClass, String imagePath) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.gearbox = gearbox;
        this.manufacturedYear = manufacturedYear;
        this.engineType = engineType;
        this.pricePerDay = pricePerDay;
        this.vin = vin;
        this.carClass = carClass;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public GearboxType getGearbox() {
        return gearbox;
    }

    public Year getManufacturedYear() {
        return manufacturedYear;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public String getVin() {
        return vin;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (!brand.equals(car.brand)) return false;
        if (!model.equals(car.model)) return false;
        if (!color.equals(car.color)) return false;
        if (!mileage.equals(car.mileage)) return false;
        if (gearbox != car.gearbox) return false;
        if (!manufacturedYear.equals(car.manufacturedYear)) return false;
        if (engineType != car.engineType) return false;
        if (!pricePerDay.equals(car.pricePerDay)) return false;
        if (!vin.equals(car.vin)) return false;
        if (carClass != car.carClass) return false;
        return imagePath != null ? imagePath.equals(car.imagePath) : car.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + mileage.hashCode();
        result = 31 * result + gearbox.hashCode();
        result = 31 * result + manufacturedYear.hashCode();
        result = 31 * result + engineType.hashCode();
        result = 31 * result + pricePerDay.hashCode();
        result = 31 * result + vin.hashCode();
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", mileage=").append(mileage);
        sb.append(", gearbox=").append(gearbox);
        sb.append(", manufacturedYear=").append(manufacturedYear);
        sb.append(", engineType=").append(engineType);
        sb.append(", pricePerDay=").append(pricePerDay);
        sb.append(", vin='").append(vin).append('\'');
        sb.append(", carClass='").append(carClass).append('\'');
        sb.append(", imagePath='").append(imagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
