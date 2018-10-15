import com.google.inject.Binder;
import com.google.inject.Module;
import controllers.CounterPartyController;
import controllers.TransferController;
import persistence.CounterPartyRepository;
import persistence.TransferRepository;

public class RevolutTransferModule implements Module {

    public void configure(Binder binder) {
        binder.bind(TransferController.class);
        binder.bind(CounterPartyController.class);
        binder.bind(CounterPartyRepository.class).asEagerSingleton();
        binder.bind(TransferRepository.class).asEagerSingleton();
    }
}
