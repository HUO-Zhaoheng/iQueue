
public class Triage_System {

	private Office[] AllOfficeInfo;
	private vector<Patient> AllPatientInfo;
	private Json response;

public:

	//Initial
	void Initial(String user, String password) {

		try {

			Connect();// Connect to Client and Database

		} catch {}

		LoadAll();// Get all 

		Check(user, password);

		set_response;
	};

	//Load all QueueInfo, DoctorInfo, PatientInfo
	void LoadAll() {

		//Load data from Database

	}; 

	// Connet to Client and Database
	void Connect() {

		//Build connection to Client and Database
		if (not connet)
			throw error;

	};

	// Return all Data
	void GetAll() {

		set_response;	// Return all Data

	}

	// Switch Queue for one particular patient
	bool SwitchQueue(String patientID, String officeID, String current_doctorID, String current_queueID,
		String target_queueID, String target_doctorID ) {

		comfirm patient from current queue;

		if (not exist) 
			throw error;

		delete patient from current queue;

		add patient to target queue(of target doctor);

		if (fail)
			throw error;

		set_response;

		return 1;
	}

	// Switch Office for particulat patient
	bool SwitchOffice(String patientID, String current_officeID, String current_doctorID, String current_queueID,
		String target_officeID) {

		comfirm patient from current queue;

		if (not exist) 
			throw error;

		delete patient from current queue;

		add patient to *First_Diagnosis* queue of target office;

		if (fail)
			throw error;

		set_response;

		return 1;
	}

	// Set patient information(include patient sign information)
	bool SetPatient(String patientID, String info);

	// Set Office Information
	bool SetOfficeInfo(String officeID, String info);

	// set Doctor Infomation
	bool SetDoctorInfo(String doctorId, String info);

	// Add patient
	bool AddPatient(Patient patient);

	// Add Office
	bool AddOffice(Office office);

	// Add Doctor
	bool AddDoctor(Doctor doctor);

	// Doctor_next
	bool Doctor_Next(String doctorID);

	// sign in
	bool Check(String user, String password) {
		if (correct) {
			return 1;
		}
		return 0;
	};

	bool NewUser(String user, String password) {

		if (no_same_username) {
			sign_up;
			return 1;
		}
		return 0;

	}

	json Execute(Json json) {

		explain json;	// Get op code and paramaters

		switch(op code) {
			case 0:
			Initial(); break;	// Initial
			case 1:
			GetAll(); break;	// Refresh request from client
			case 2:
			try {
				SwitchQueue(patientID, officeID, current_queueID, target_queueID,
				target_doctorID);	// Switch Queue for particular patient
			} catch {}
			break;
			case 3:
			try {
				SwitchOffice(patientID, current_officeID,
				current_queueID, target_officeID);	// Switch Office for particular patient
			} catch {}
			break;
			case 4:

				SetPatient(patientID, info);
		
			break;	// set PatientInfo
			case 5:

				SetOfficeInfo(officeID, info);
			
			break;	// set OfficeInfo
			case 6:

				SetDoctorInfo(doctorID, info);
			
			break;	// set DoctorInfo
			case 7:
			AddPatient(); break;
			case 8:
			AddOffice(); break;
			case 9:
			AddDoctor(); break;
			case 10:
			Doctor_Next(String doctorID); break;
		}

		send Json; // send json back to client
	}
	
}