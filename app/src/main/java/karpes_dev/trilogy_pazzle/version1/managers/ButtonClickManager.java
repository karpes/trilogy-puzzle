package karpes_dev.trilogy_pazzle.version1.managers;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import karpes_dev.trilogy_pazzle.R;
import karpes_dev.trilogy_pazzle.version1.abstractions.AFigureView;
import karpes_dev.trilogy_pazzle.version2.interfaces.ICommand;
import karpes_dev.trilogy_pazzle.version1.abstractions.IFigure;
import karpes_dev.trilogy_pazzle.version1.abstractions.IManager;
import karpes_dev.trilogy_pazzle.version1.abstractions.IMathPolygon;
import karpes_dev.trilogy_pazzle.version1.abstractions.IPermission;
import karpes_dev.trilogy_pazzle.version1.abstractions.IPolygonListenerManager;
import karpes_dev.trilogy_pazzle.version1.command.CommandCenter;
import karpes_dev.trilogy_pazzle.version1.command.CommandDownload;
import karpes_dev.trilogy_pazzle.version1.command.CommandInstruction;
import karpes_dev.trilogy_pazzle.version1.command.CommandRestart;
import karpes_dev.trilogy_pazzle.version1.command.CommandSetting;

public class ButtonClickManager implements IManager<ICommand> {

    private int id;
    private View viewParent;
    private IPermission storagePermission;
    private AFigureView figureView;
    private IFigure figure;
    private IMathPolygon mathPolygon;
    private IPolygonListenerManager polygonListenerManager;
    private ViewGroup relativeLayoutContainer;

    private SparseArray<ICommand> commandSparseArray;

    public ButtonClickManager(int id, IFigure figure, View viewParent, IPermission storagePermission, AFigureView figureView, IMathPolygon mathPolygon, IPolygonListenerManager polygonListenerManager, ViewGroup relativeLayoutContainer) {
        this.id = id;
        this.figure = figure;
        this.viewParent = viewParent;
        this.storagePermission = storagePermission;
        this.figureView = figureView;
        this.mathPolygon = mathPolygon;
        this.polygonListenerManager = polygonListenerManager;
        this.relativeLayoutContainer = relativeLayoutContainer;

        commandSparseArray = new SparseArray<>();
        init();
    }

    public ICommand get(int keyId){
        return commandSparseArray.get(keyId);
    }

    private void init() {

        ICommand commandCenter = new CommandCenter(relativeLayoutContainer);
        commandSparseArray.append(viewParent.findViewById(R.id.fb_center).getId(), commandCenter);

        ICommand commandDownload = new CommandDownload(figureView, storagePermission);
        commandSparseArray.append(viewParent.findViewById(R.id.fb_download).getId(), commandDownload);

        ICommand commandRestart = new CommandRestart(id, figure, figureView, mathPolygon, polygonListenerManager, relativeLayoutContainer);
        commandSparseArray.append(viewParent.findViewById(R.id.fb_restart).getId(), commandRestart);

        ICommand commandSetting = new CommandSetting();
        commandSparseArray.append(viewParent.findViewById(R.id.fb_setting).getId(), commandSetting);

        ICommand commandInstruction = new CommandInstruction();
        commandSparseArray.append(viewParent.findViewById(R.id.fb_instruction).getId(), commandInstruction);
    }


}
