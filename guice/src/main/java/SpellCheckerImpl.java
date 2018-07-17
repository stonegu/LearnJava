class SpellCheckerImpl implements SpellChecker {
    @Override
    @CallTracker
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
}