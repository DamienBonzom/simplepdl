/**
 */
package simplepdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gestion Ressources</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.GestionRessources#getQuantite <em>Quantite</em>}</li>
 *   <li>{@link simplepdl.GestionRessources#getRessources <em>Ressources</em>}</li>
 * </ul>
 *
 * @see simplepdl.SimplepdlPackage#getGestionRessources()
 * @model
 * @generated
 */
public interface GestionRessources extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Quantite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantite</em>' attribute.
	 * @see #setQuantite(int)
	 * @see simplepdl.SimplepdlPackage#getGestionRessources_Quantite()
	 * @model required="true"
	 * @generated
	 */
	int getQuantite();

	/**
	 * Sets the value of the '{@link simplepdl.GestionRessources#getQuantite <em>Quantite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantite</em>' attribute.
	 * @see #getQuantite()
	 * @generated
	 */
	void setQuantite(int value);

	/**
	 * Returns the value of the '<em><b>Ressources</b></em>' reference list.
	 * The list contents are of type {@link simplepdl.Ressource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ressources</em>' reference list.
	 * @see simplepdl.SimplepdlPackage#getGestionRessources_Ressources()
	 * @model
	 * @generated
	 */
	EList<Ressource> getRessources();

} // GestionRessources
