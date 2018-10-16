import com.google.inject.Binder;
import com.google.inject.Module;
import controllers.account.AccountController;
import controllers.counterparty.CounterPartyController;
import controllers.transfer.TransferController;
import persistence.AccountRepository;
import persistence.CounterPartyRepository;
import persistence.TransferRepository;

public class RevolutTransferModule implements Module {

    public void configure(Binder binder) {
        binder.bind(TransferController.class);
        binder.bind(CounterPartyController.class);
        binder.bind(AccountController.class);
        binder.bind(CounterPartyRepository.class).asEagerSingleton();
        binder.bind(TransferRepository.class).asEagerSingleton();
        binder.bind(AccountRepository.class).asEagerSingleton();
    }
}
