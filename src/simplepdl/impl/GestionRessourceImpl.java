/**
 */
package simplepdl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import simplepdl.GestionRessource;
import simplepdl.Ressource;
import simplepdl.SimplepdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gestion Ressource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.impl.GestionRessourceImpl#getQuantite <em>Quantite</em>}</li>
 *   <li>{@link simplepdl.impl.GestionRessourceImpl#getRessource <em>Ressource</em>}</li>
 *   <li>{@link simplepdl.impl.GestionRessourceImpl#isUtilisee <em>Utilisee</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GestionRessourceImpl extends ProcessElementImpl implements GestionRessource {
	/**
	 * The default value of the '{@link #getQuantite() <em>Quantite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantite()
	 * @generated
	 * @ordered
	 */
	protected static final int QUANTITE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getQuantite() <em>Quantite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantite()
	 * @generated
	 * @ordered
	 */
	protected int quantite = QUANTITE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRessource() <em>Ressource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRessource()
	 * @generated
	 * @ordered
	 */
	protected Ressource ressource;

	/**
	 * The default value of the '{@link #isUtilisee() <em>Utilisee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUtilisee()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UTILISEE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUtilisee() <em>Utilisee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUtilisee()
	 * @generated
	 * @ordered
	 */
	protected boolean utilisee = UTILISEE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GestionRessourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimplepdlPackage.Literals.GESTION_RESSOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getQuantite() {
		return quantite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setQuantite(int newQuantite) {
		int oldQuantite = quantite;
		quantite = newQuantite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimplepdlPackage.GESTION_RESSOURCE__QUANTITE, oldQuantite, quantite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Ressource getRessource() {
		if (ressource != null && ressource.eIsProxy()) {
			InternalEObject oldRessource = (InternalEObject)ressource;
			ressource = (Ressource)eResolveProxy(oldRessource);
			if (ressource != oldRessource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE, oldRessource, ressource));
			}
		}
		return ressource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ressource basicGetRessource() {
		return ressource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRessource(Ressource newRessource) {
		Ressource oldRessource = ressource;
		ressource = newRessource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE, oldRessource, ressource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUtilisee() {
		return utilisee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUtilisee(boolean newUtilisee) {
		boolean oldUtilisee = utilisee;
		utilisee = newUtilisee;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimplepdlPackage.GESTION_RESSOURCE__UTILISEE, oldUtilisee, utilisee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimplepdlPackage.GESTION_RESSOURCE__QUANTITE:
				return getQuantite();
			case SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE:
				if (resolve) return getRessource();
				return basicGetRessource();
			case SimplepdlPackage.GESTION_RESSOURCE__UTILISEE:
				return isUtilisee();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SimplepdlPackage.GESTION_RESSOURCE__QUANTITE:
				setQuantite((Integer)newValue);
				return;
			case SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE:
				setRessource((Ressource)newValue);
				return;
			case SimplepdlPackage.GESTION_RESSOURCE__UTILISEE:
				setUtilisee((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SimplepdlPackage.GESTION_RESSOURCE__QUANTITE:
				setQuantite(QUANTITE_EDEFAULT);
				return;
			case SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE:
				setRessource((Ressource)null);
				return;
			case SimplepdlPackage.GESTION_RESSOURCE__UTILISEE:
				setUtilisee(UTILISEE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SimplepdlPackage.GESTION_RESSOURCE__QUANTITE:
				return quantite != QUANTITE_EDEFAULT;
			case SimplepdlPackage.GESTION_RESSOURCE__RESSOURCE:
				return ressource != null;
			case SimplepdlPackage.GESTION_RESSOURCE__UTILISEE:
				return utilisee != UTILISEE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (quantite: ");
		result.append(quantite);
		result.append(", utilisee: ");
		result.append(utilisee);
		result.append(')');
		return result.toString();
	}

} //GestionRessourceImpl
