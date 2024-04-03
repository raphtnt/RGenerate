package be.raphtnt;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private final List<String> columns;

//    public Table(String name, ArrayList<String> columns) {
    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void addColumn(String column) {
        this.columns.add(column);
    }
}
