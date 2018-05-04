package lifecycle;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoggingPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("AFTER PHASE: " + event.getPhaseId().toString());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("BEFORE PHASE: " + event.getPhaseId().toString());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
