package scale;

import model.Automobile;

/* Name: Mujing Zhou
 * Andrew ID: mujingz
 * Date: Jun. 17 2015
 * 
 * Automotive -- This class extends the Thread class and will implement five defined
 * synchronized methods which are called in ProxyAutomobile in their own thread.
 * operationNum field is used to distinguish different methods and operation field is used
 * as the input parameter used to call methods in Automobile class.
 */

public class EditOptions extends Thread {

    private int operationNum;
    private String operation[];
    private Automobile auto;

    /*
     * EditOptions -- default constructor of EditOptions class with no argument.
     */
    public EditOptions() {
    }

    /*
     * EditOptions -- constructor of EditOptions class with one argument which initialize
     * the operationNum field.
     */
    public EditOptions(int opNum) {
        operationNum = opNum;
    }

    /*
     * EditOptions -- constructor of EditOptions class with three arguments which initialize
     * the operationNum, operation and auto fields.
     */
    public EditOptions(Automobile a1, String[] op, int opNum) {
        setOperationNum(opNum);
        setAuto(a1);
        setOperation(op);
    }

    /*
     * setOperationNum -- set the operationNum field of the EditOptions class.
     */
    public void setOperationNum(int operationNum) {
        this.operationNum = operationNum;
    }

    /*
     * setAuto -- set the auto field of the EditOptions class.
     */
    public void setAuto(Automobile auto) {
        this.auto = auto;
    }

    /*
     * setOperation -- set the operation field of the EditOptions class.
     */
    public void setOperation(String[] operation) {
        this.operation = operation.clone();
    }

    /*
     * getOperationNum -- get the operationNum field of the EditOptions class.
     */
    public int getOperationNum() {
        return operationNum;
    }
    
    /*
     * getOperation -- get the operation field of the EditOptions class.
     */
    public String[] getOperation() {
        return operation;
    }

    /*
     * getAuto -- get the auto field of the EditOptions class.
     */
    public Automobile getAuto() {
        return auto;
    }

    /*
     * run -- override the default run method of Thread class.
     * Based on the value of the operationNum field, switch statement will choose to
     * execute different methods. A thread will be created in each case and wait() notify() methods
     * are used for synchronize goal.
     */
    @Override
    public void run() {
        String[] op = getOperation();
        synchronized (getAuto()) {
            switch (getOperationNum()) {

            /*
             * case 1 -- Update the option set name. 
             * case 2 -- Update the option name in a given option set. 
             * case 3 -- Update the price for a given option. 
             * case 4 -- Delete a given option set. 
             * case 5 -- Delete an option in a given option set.
             */

            case 1: {

                getAuto().updateOptionSet(op[0], op[1]);
                System.out.println("finished Updating OptionSet: " + op[0]);
                getAuto().notifyAll();
                break;
            }

            case 2: {
                getAuto().updateOption(op[0], op[1], op[2]);
                System.out.println("finished Updating Option Name: " + op[1]
                        + " in OptionSet: " + op[0]);
                getAuto().notifyAll();
                break;
            }

            case 3: {
                getAuto().updateOption(op[0], op[1], Float.valueOf(op[2]));
                System.out.println("finished Updating Price in Option: "
                        + op[1] + " in OptionSet: " + op[0]);
                getAuto().notifyAll();
                break;
            }

            case 4: {
                getAuto().deleteOptionSet(op[0]);
                System.out.println("succeed to delete the optionSet: " + op[0]);
                getAuto().notifyAll();
                break;
            }

            case 5: {
                getAuto().deleteOption(op[0], op[1]);
                System.out.println("succeed to delete the option: " + op[1]
                        + " in option set: " + op[0]);
                getAuto().notifyAll();
                break;
            }

            }
        }
    }
}
