package com.example.healthservice.domain.repository;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.util.Base58;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HealthAnalysisRepository {

    public Asset saveData(Object data) {
        try {
            // Создаем актив
            Asset assetData = new Asset((String) data);

            // Метаданные (могут быть любыми, например, информация о создателе и т. д.)
            MetaData metaData = new MetaData();

            // Создаем транзакцию для сохранения данных
            Transaction createTransaction = BigchainDbTransactionBuilder.init()
                    .addAssets(assetData, Object.class)
                    .operation(Operations.CREATE)
                    .sendTransaction();

            return createTransaction.getAsset();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод для поиска данных в BigchainDB
    public List<Transaction> findData(String searchData) {
        try {
            // Строим запрос для поиска данных по определенным критериям
            List<Transaction> transactions = QueryBuilder
                    .createQuery()
                    .addCondition("data", searchData)
                    .setLimit(10)
                    .execQuery();

            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
