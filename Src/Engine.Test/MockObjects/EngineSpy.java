public class EngineSpy implements IEngineBlock {

    private boolean _isSignaled;

    public boolean GetIsSignaled(){
        return _isSignaled;
    }

    @Override
    public void Signal() {
        _isSignaled = true;
    }
}
