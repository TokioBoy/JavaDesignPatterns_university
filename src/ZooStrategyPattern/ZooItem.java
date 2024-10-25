package ZooStrategyPattern;

public class ZooItem implements ElectricalConsumption, WaterConsumption {
    private ElectricalConsumer electricalConsumer;
    private WaterConsumer waterConsumer;

    public ZooItem(ElectricalConsumer electricalConsumer, WaterConsumer waterConsumer) {
        this.electricalConsumer = electricalConsumer;
        this.waterConsumer = waterConsumer;
    }

    public double getWaterConsumption() {
        return electricalConsumer.getElectricityConsumption();
    }

    public double getElectricityConsumption() {
        return waterConsumer.getWaterConsumption();
    }
}
