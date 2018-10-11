package services;

import models.CounterParty;
import models.CounterPartyEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.CounterPartyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterPartyCreatorTest {

    private CounterPartyCreator counterPartyCreator;

    @Mock
    private CounterPartyRepository counterPartyRepository;

    @Before
    public void setUp() {
        counterPartyCreator = new CounterPartyCreator(counterPartyRepository);
    }

    @Test
    public void callCounterPartyRepositoryToSaveOnDatabaseWithRightValues() {
        CounterParty counterParty = getCounterParty();

        CounterPartyEntity savedCounterPartyEntity = new CounterPartyEntity(counterParty);

        when(counterPartyRepository.persist(any(CounterPartyEntity.class))).thenReturn(savedCounterPartyEntity);

        counterPartyCreator.perform(counterParty);

        ArgumentCaptor<CounterPartyEntity> captor = ArgumentCaptor.forClass(CounterPartyEntity.class);

        verify(counterPartyRepository).persist(captor.capture());

        CounterPartyEntity expectedEntity = new CounterPartyEntity(counterParty);

        assertThat(captor.getValue()).isEqualToComparingFieldByFieldRecursively(expectedEntity);
    }

    @Test
    public void returnSavedCounterPartyValues() {
        CounterParty counterParty = getCounterParty();

        CounterPartyEntity savedCounterPartyEntity = new CounterPartyEntity(counterParty);

        when(counterPartyRepository.persist(any(CounterPartyEntity.class))).thenReturn(savedCounterPartyEntity);

        CounterParty savedCounterParty = counterPartyCreator.perform(counterParty);

        assertThat(savedCounterParty).isEqualToComparingFieldByFieldRecursively(counterParty);
    }

    private CounterParty getCounterParty() {
        CounterParty counterParty = mock(CounterParty.class);

        when(counterParty.getId()).thenReturn(1);
        when(counterParty.getName()).thenReturn("Counter Party Name");
        when(counterParty.getCountry()).thenReturn("BRA");
        when(counterParty.getIdentificationNumber()).thenReturn("123456789");

        return counterParty;
    }
}