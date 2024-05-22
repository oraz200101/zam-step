package com.example.cryptocurrencyservice.domain.entity;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.3.
 */
@SuppressWarnings("rawtypes")
public class ZamToken extends Contract {
    public static final String BINARY = "60806040526040518060400160405280600881526020017f5a616d546f6b656e0000000000000000000000000000000000000000000000008152505f90816100479190610388565b506040518060400160405280600381526020017f5a414d00000000000000000000000000000000000000000000000000000000008152506001908161008c9190610388565b50601260025f6101000a81548160ff021916908360ff1602179055503480156100b3575f80fd5b5060405161140538038061140583398181016040528101906100d59190610485565b60025f9054906101000a900460ff1660ff16600a6100f3919061060c565b816100fe9190610656565b60038190555060035460045f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f208190555050610697565b5f81519050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f60028204905060018216806101c957607f821691505b6020821081036101dc576101db610185565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f6008830261023e7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610203565b6102488683610203565b95508019841693508086168417925050509392505050565b5f819050919050565b5f819050919050565b5f61028c61028761028284610260565b610269565b610260565b9050919050565b5f819050919050565b6102a583610272565b6102b96102b182610293565b84845461020f565b825550505050565b5f90565b6102cd6102c1565b6102d881848461029c565b505050565b5b818110156102fb576102f05f826102c5565b6001810190506102de565b5050565b601f82111561034057610311816101e2565b61031a846101f4565b81016020851015610329578190505b61033d610335856101f4565b8301826102dd565b50505b505050565b5f82821c905092915050565b5f6103605f1984600802610345565b1980831691505092915050565b5f6103788383610351565b9150826002028217905092915050565b6103918261014e565b67ffffffffffffffff8111156103aa576103a9610158565b5b6103b482546101b2565b6103bf8282856102ff565b5f60209050601f8311600181146103f0575f84156103de578287015190505b6103e8858261036d565b86555061044f565b601f1984166103fe866101e2565b5f5b8281101561042557848901518255600182019150602085019450602081019050610400565b86831015610442578489015161043e601f891682610351565b8355505b6001600288020188555050505b505050505050565b5f80fd5b61046481610260565b811461046e575f80fd5b50565b5f8151905061047f8161045b565b92915050565b5f6020828403121561049a57610499610457565b5b5f6104a784828501610471565b91505092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f8160011c9050919050565b5f808291508390505b60018511156105325780860481111561050e5761050d6104b0565b5b600185161561051d5780820291505b808102905061052b856104dd565b94506104f2565b94509492505050565b5f8261054a5760019050610605565b81610557575f9050610605565b816001811461056d5760028114610577576105a6565b6001915050610605565b60ff841115610589576105886104b0565b5b8360020a9150848211156105a05761059f6104b0565b5b50610605565b5060208310610133831016604e8410600b84101617156105db5782820a9050838111156105d6576105d56104b0565b5b610605565b6105e884848460016104e9565b925090508184048111156105ff576105fe6104b0565b5b81810290505b9392505050565b5f61061682610260565b915061062183610260565b925061064e7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff848461053b565b905092915050565b5f61066082610260565b915061066b83610260565b925082820261067981610260565b915082820484148315176106905761068f6104b0565b5b5092915050565b610d61806106a45f395ff3fe608060405234801561000f575f80fd5b5060043610610091575f3560e01c8063313ce56711610064578063313ce5671461013157806370a082311461014f57806395d89b411461017f578063a9059cbb1461019d578063dd62ed3e146101cd57610091565b806306fdde0314610095578063095ea7b3146100b357806318160ddd146100e357806323b872dd14610101575b5f80fd5b61009d6101fd565b6040516100aa9190610934565b60405180910390f35b6100cd60048036038101906100c891906109e5565b610288565b6040516100da9190610a3d565b60405180910390f35b6100eb610375565b6040516100f89190610a65565b60405180910390f35b61011b60048036038101906101169190610a7e565b61037b565b6040516101289190610a3d565b60405180910390f35b61013961065b565b6040516101469190610ae9565b60405180910390f35b61016960048036038101906101649190610b02565b61066d565b6040516101769190610a65565b60405180910390f35b610187610682565b6040516101949190610934565b60405180910390f35b6101b760048036038101906101b291906109e5565b61070e565b6040516101c49190610a3d565b60405180910390f35b6101e760048036038101906101e29190610b2d565b6108a4565b6040516101f49190610a65565b60405180910390f35b5f805461020990610b98565b80601f016020809104026020016040519081016040528092919081815260200182805461023590610b98565b80156102805780601f1061025757610100808354040283529160200191610280565b820191905f5260205f20905b81548152906001019060200180831161026357829003601f168201915b505050505081565b5f8160055f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f20819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040516103639190610a65565b60405180910390a36001905092915050565b60035481565b5f60045f8573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f20548211156103fc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103f390610c12565b60405180910390fd5b60055f8573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f20548211156104b7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ae90610c7a565b60405180910390fd5b8160045f8673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546105039190610cc5565b925050819055508160045f8573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546105569190610cf8565b925050819055508160055f8673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546105e49190610cc5565b925050819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516106489190610a65565b60405180910390a3600190509392505050565b60025f9054906101000a900460ff1681565b6004602052805f5260405f205f915090505481565b6001805461068f90610b98565b80601f01602080910402602001604051908101604052809291908181526020018280546106bb90610b98565b80156107065780601f106106dd57610100808354040283529160200191610706565b820191905f5260205f20905b8154815290600101906020018083116106e957829003601f168201915b505050505081565b5f8160045f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f2054101561078f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161078690610c12565b60405180910390fd5b8160045f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546107db9190610cc5565b925050819055508160045f8573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f82825461082e9190610cf8565b925050819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516108929190610a65565b60405180910390a36001905092915050565b6005602052815f5260405f20602052805f5260405f205f91509150505481565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f601f19601f8301169050919050565b5f610906826108c4565b61091081856108ce565b93506109208185602086016108de565b610929816108ec565b840191505092915050565b5f6020820190508181035f83015261094c81846108fc565b905092915050565b5f80fd5b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61098182610958565b9050919050565b61099181610977565b811461099b575f80fd5b50565b5f813590506109ac81610988565b92915050565b5f819050919050565b6109c4816109b2565b81146109ce575f80fd5b50565b5f813590506109df816109bb565b92915050565b5f80604083850312156109fb576109fa610954565b5b5f610a088582860161099e565b9250506020610a19858286016109d1565b9150509250929050565b5f8115159050919050565b610a3781610a23565b82525050565b5f602082019050610a505f830184610a2e565b92915050565b610a5f816109b2565b82525050565b5f602082019050610a785f830184610a56565b92915050565b5f805f60608486031215610a9557610a94610954565b5b5f610aa28682870161099e565b9350506020610ab38682870161099e565b9250506040610ac4868287016109d1565b9150509250925092565b5f60ff82169050919050565b610ae381610ace565b82525050565b5f602082019050610afc5f830184610ada565b92915050565b5f60208284031215610b1757610b16610954565b5b5f610b248482850161099e565b91505092915050565b5f8060408385031215610b4357610b42610954565b5b5f610b508582860161099e565b9250506020610b618582860161099e565b9150509250929050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610baf57607f821691505b602082108103610bc257610bc1610b6b565b5b50919050565b7f496e73756666696369656e742062616c616e63650000000000000000000000005f82015250565b5f610bfc6014836108ce565b9150610c0782610bc8565b602082019050919050565b5f6020820190508181035f830152610c2981610bf0565b9050919050565b7f416c6c6f77616e636520657863656564656400000000000000000000000000005f82015250565b5f610c646012836108ce565b9150610c6f82610c30565b602082019050919050565b5f6020820190508181035f830152610c9181610c58565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610ccf826109b2565b9150610cda836109b2565b9250828203905081811115610cf257610cf1610c98565b5b92915050565b5f610d02826109b2565b9150610d0d836109b2565b9250828201905080821115610d2557610d24610c98565b5b9291505056fea264697066735822122019e496dd2c9d43d87485e6bcf7b878575cef27e762895819c8cc260c2188b94464736f6c63430008190033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ZamToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ZamToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ZamToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ZamToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

//    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
//        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

//    public static List<TransferEventResponse> getTransferEvents(Log transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
//        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            TransferEventResponse typedResponse = new TransferEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String param0, String param1) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ZamToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ZamToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ZamToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ZamToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ZamToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ZamToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ZamToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ZamToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ZamToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialSupply)));
        return deployRemoteCall(ZamToken.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ZamToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialSupply)));
        return deployRemoteCall(ZamToken.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ZamToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialSupply)));
        return deployRemoteCall(ZamToken.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ZamToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialSupply)));
        return deployRemoteCall(ZamToken.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

//    public static void linkLibraries(List<Contract.LinkReference> references) {
//        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
//    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
