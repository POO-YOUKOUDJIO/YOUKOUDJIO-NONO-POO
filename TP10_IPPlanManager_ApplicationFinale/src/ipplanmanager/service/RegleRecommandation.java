package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.util.ArrayList;

public interface RegleRecommandation {
    // On change 'analyser' en 'evaluer' et on accepte une liste
    ArrayList<Recommandation> evaluer(ArrayList<VLAN> vlans);
}