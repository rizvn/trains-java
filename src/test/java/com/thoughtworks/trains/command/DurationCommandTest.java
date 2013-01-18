package com.thoughtworks.trains.command;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thoughtworks.trains.Commuter;

@RunWith(MockitoJUnitRunner.class)
public class DurationCommandTest {
    @Mock
    private Commuter commuter;
    
    @Test
    public void executeShouldInvokeRouteDuration() {
        DurationCommand command = new DurationCommand("DURATION: A-B-C", System.out);
        List<String> intermediate = new ArrayList<String>();
        intermediate.add("B");
        
        command.execute(commuter);
        
        verify(commuter).routeDuration("A", "C", intermediate);
    }
    
    @Test
    public void executeShouldInvokeRouteDurationWithMultipleItermediateStops() {
        DurationCommand command = new DurationCommand("DURATION: A-B-C-D-E-F", System.out);
        List<String> intermediate = new ArrayList<String>();
        intermediate.add("B");
        intermediate.add("C");
        intermediate.add("D");
        intermediate.add("E");
        
        command.execute(commuter);
        
        verify(commuter).routeDuration("A", "F", intermediate);
    }

}
