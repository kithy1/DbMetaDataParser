//package com.kandk.DbMetaDataParser.service;
//
//import com.kandk.DbMetaDataParser.model.Column;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//@Component
//public class BootstrapClass implements ApplicationListener<ContextRefreshedEvent> {
//    ColumnNameRetriever columnNameRetriever;
//
//    public BootstrapClass(ColumnNameRetriever columnNameRetriever) {
//        this.columnNameRetriever = columnNameRetriever;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        start();
//    }
//
//    private void start(){
////        Map<String, List<String>> columnNames = columnNameRetriever.getColumnNames();
////        Set<Map.Entry<String, List<String>>> entries = columnNames.entrySet();
////        for (Map.Entry<String, List<String>> entry : entries) {
////            System.out.println(entry.getKey());
////        }
//        System.out.println("......................................................");
//        Map<String, List<Column>> sprawy = columnNameRetriever.getColumnData(null, null, "user_roles", null);
//        Set<Map.Entry<String, List<Column>>> entries = sprawy.entrySet();
//        for (Map.Entry<String, List<Column>> entry : entries) {
//            String tableName = entry.getKey();
//            System.out.println(tableName);
//            List<Column> value = entry.getValue();
//            value.stream().forEach(column -> System.out.println(new EntityBuilder().prepareColumnUnit(column)));
//        }
//
//    }
//}
