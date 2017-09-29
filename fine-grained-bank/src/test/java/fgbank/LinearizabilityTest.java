package fgbank;


import com.devexperts.dxlab.lincheck.LinChecker;
import com.devexperts.dxlab.lincheck.annotations.HandleExceptionAsResult;
import com.devexperts.dxlab.lincheck.annotations.Operation;
import com.devexperts.dxlab.lincheck.annotations.Param;
import com.devexperts.dxlab.lincheck.annotations.Reset;
import com.devexperts.dxlab.lincheck.paramgen.IntGen;
import com.devexperts.dxlab.lincheck.paramgen.LongGen;
import com.devexperts.dxlab.lincheck.stress.StressCTest;
import org.junit.Test;

/**
 * This test checks bank implementation for linearizability
 */
@Param(name = "id", gen = IntGen.class, conf = "0:4")
@Param(name = "amount", gen = LongGen.class, conf = "1:100")
@StressCTest
public class LinearizabilityTest {
    private Bank bank;

    @Reset
    public void reset() {
        bank = new BankImpl(5);
    }

    @Operation(params = {"id"})
    public long getAmount(int id) {
        return bank.getAmount(id);
    }

    @Operation
    public long getTotalAmount() {
        return bank.getTotalAmount();
    }

    @HandleExceptionAsResult(IllegalStateException.class)
    @Operation(params = {"id", "amount"})
    public long deposit(int id, long amount) {
        return bank.deposit(id, amount);
    }

    @HandleExceptionAsResult(IllegalStateException.class)
    @Operation(params = {"id", "amount"})
    public long withdraw(int id, long amount) {
        return bank.withdraw(id, amount);
    }

    @HandleExceptionAsResult(IllegalStateException.class)
    @Operation(params = {"id", "id", "amount"})
    public void transfer(int idFrom, int idTo, long amount) {
        if (idFrom != idTo)
            bank.transfer(idFrom, idTo, amount);
    }

    @Test
    public void test() {
        LinChecker.check(LinearizabilityTest.class);
    }
}