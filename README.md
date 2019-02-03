# jME3 project to show how to access the appStates in the StateManager object.

## Objective is to be able to see which app states are currently attached to the intantiated jme3 state manager object.

The intent of this code is for debug usage, to see which app states are currently attached to the stateManager object.

The debug info is written out to the console by System.out statements.

When a programmer has an idea of what appState objects are currently attached to the appStateManager, then he knows which calls to make to get rid of unwanted states.  This is especially true if you want to empty the stateManager before filling it up with custom made appStates.  This is my use-case anyway.
