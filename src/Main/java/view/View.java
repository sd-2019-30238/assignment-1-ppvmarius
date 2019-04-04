package view;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru crearea tabelelor pentru clienti, produse si comenzi.
 */
public class View {

    /**
     * Creeaza, utilizand reflection, un obiect de tipul TableModel avand coloanele specifice pentru fiecare obiect apelant.
     * @param beanClass
     * @param list
     * @param <T>
     * @return
     */
    public static <T> TableModel createTableModel(Class<T> beanClass, List<T> list) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            List<String> columns = new ArrayList<>();
            List<Method> getters = new ArrayList<>();

            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                String name = pd.getName();
                if (name.equals("class")) {
                    continue;
                }
                if (name.equals("id")) {
                    columns.add(0, name);
                    Method m = pd.getReadMethod();
                    getters.add(0, m);
                } else {
                    if (name.contains("nume")) {
                        columns.add(1, name);
                        Method m = pd.getReadMethod();
                        getters.add(1, m);
                    } else {
                        columns.add(name);
                        Method m = pd.getReadMethod();
                        getters.add(m);
                    }
                }
            }


            TableModel model = new AbstractTableModel() {
                @Override
                public String getColumnName(int column) {
                    return columns.get(column);
                }

                @Override
                public int getRowCount() {
                    return list.size();
                }

                @Override
                public int getColumnCount() {
                    return columns.size();
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    try {
                        return getters.get(columnIndex).invoke(list.get(rowIndex));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            };
            return model;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
