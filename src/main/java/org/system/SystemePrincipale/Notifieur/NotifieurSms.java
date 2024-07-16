package org.system.SystemePrincipale.Notifieur;

import org.system.SystemePrincipale.Ecouteurs.IListener;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Medecin;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Patient;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;
import org.system.SystemePrincipale.Event.Event;
import org.system.SystemePrincipale.Event.RendezVous;
import org.system.SystemePrincipale.Event.ResultatExamenLaboratoire;
import org.system.SystemePrincipale.Registre;
import org.system.SystemePrincipale.Vue.NotificationPanel;

public class NotifieurSms implements INotifieur{

private NotificationPanel panel;

    public NotifieurSms(NotificationPanel panel) {
        this.panel = panel;

    }

    @Override
        public void notify(Event event) {
            Registre registre = Registre.getInstance();


            switch (event.getType()) {
                case RDV:
                    envoieNotifications((RendezVous) event, registre);
                    break;
                case RESULTAT_EXAMEN:
                    envoieNotifications((ResultatExamenLaboratoire) event, registre);
                    break;
            }
        }

        private void envoieNotifications(RendezVous event, Registre registre) {

            IListener patient = registre.getPatient(event.getCodePatient());
            IListener docteur = registre.getMedecin(((Patient) patient).getCodeMedecin());
            IListener service = ((Medecin) docteur).getServiceAssocie();

            String notificationMessage = createNotificationMessage(event);

            service.setNotificationType("sms");
            ((Service) service).setPanel(panel);
            service.handleNotification(notificationMessage);

            patient.setNotificationType("sms");
            ((Patient) patient).setPanel(panel);
            patient.handleNotification(notificationMessage);


        }

        private void envoieNotifications(ResultatExamenLaboratoire event, Registre registre) {
            IListener patient = registre.getPatient(event.getCodePatient());
            IListener docteur = registre.getMedecin(((Patient) patient).getCodeMedecin());
            IListener service = ((Medecin) docteur).getServiceAssocie();


            String notificationMessage = createNotificationMessage(event);
            docteur.setNotificationType("sms");
            ((Medecin) docteur).setPanel(panel);
            docteur.handleNotification(notificationMessage);

            service.setNotificationType("sms");
            ((Service) service).setPanel(panel);
            service.handleNotification(notificationMessage);
        }

        private String createNotificationMessage(Event event) {
            return "SMS - Event Type: " + event.getType() + ", Details: " + event.toString();
        }

}
