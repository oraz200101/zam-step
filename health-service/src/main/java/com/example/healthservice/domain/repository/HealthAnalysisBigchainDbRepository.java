package com.example.healthservice.domain.repository;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.KeyPair;

@Repository
public class HealthAnalysisBigchainDbRepository {

    @Autowired
    private KeyPair keyPair;

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
                    .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate())
                    .sendTransaction();

            return createTransaction.getAsset();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод для поиска данных в BigchainDB
//    public List<Transaction> findData(String searchData) {
//        try {
//            // Строим запрос для поиска данных по определенным критериям
//            List<Transaction> transactions = QueryBuilder
//                    .createQuery()
//                    .addCondition("data", searchData)
//                    .setLimit(10)
//                    .execQuery();
//
//            return transactions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
