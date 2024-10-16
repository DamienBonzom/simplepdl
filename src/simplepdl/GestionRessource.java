/**
 */
package simplepdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gestion Ressource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.GestionRessource#getQuantite <em>Quantite</em>}</li>
 *   <li>{@link simplepdl.GestionRessource#getRessource <em>Ressource</em>}</li>
 *   <li>{@link simplepdl.GestionRessource#isUtilisee <em>Utilisee</em>}</li>
 * </ul>
 *
 * @see simplepdl.SimplepdlPackage#getGestionRessource()
 * @model
 * @generated
 */
public interface GestionRessource extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Quantite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantite</em>' attribute.
	 * @see #setQuantite(int)
	 * @see simplepdl.SimplepdlPackage#getGestionRessource_Quantite()
	 * @model required="true"
	 * @generated
	 */
	int getQuantite();

	/**
	 * Sets the value of the '{@link simplepdl.GestionRessource#getQuantite <em>Quantite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantite</em>' attribute.
	 * @see #getQuantite()
	 * @generated
	 */
	void setQuantite(int value);

	/**
	 * Returns the value of the '<em><b>Ressource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ressource</em>' reference.
	 * @see #setRessource(Ressource)
	 * @see simplepdl.SimplepdlPackage#getGestionRessource_Ressource()
	 * @model required="true"
	 * @generated
	 */
	Ressource getRessource();

	/**
	 * Sets the value of the '{@link simplepdl.GestionRessource#getRessource <em>Ressource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ressource</em>' reference.
	 * @see #getRessource()
	 * @generated
	 */
	void setRessource(Ressource value);

	/**
	 * Returns the value of the '<em><b>Utilisee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utilisee</em>' attribute.
	 * @see #setUtilisee(boolean)
	 * @see simplepdl.SimplepdlPackage#getGestionRessource_Utilisee()
	 * @model required="true"
	 * @generated
	 */
	boolean isUtilisee();

	/**
	 * Sets the value of the '{@link simplepdl.GestionRessource#isUtilisee <em>Utilisee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utilisee</em>' attribute.
	 * @see #isUtilisee()
	 * @generated
	 */
	void setUtilisee(boolean value);

} // GestionRessource
