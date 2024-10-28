package fr.paris.lutece.util.signrequest;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class IPAuthentificator implements RequestAuthenticator {
	
	public enum CASE {
		ALLOW 
		{
			public boolean getAuthorisation( ) {
				return true;
			}
		},
		BLOCK
		{
			public boolean getAuthorisation( ) {
				return false;
			}
		};
		
		public abstract boolean getAuthorisation( );
	}
	
	private Set<String> _listIPs;
	private CASE _caseAllowedOrBlock;
	
	/**
	 * Gets the list of IP adresses
	 * 
	 * @return the list of IP adresses
	 */
	public Set<String> getIPs( ) {
		return _listIPs;
	}
	
	/**
	 * Sets the list of IP adresses
	 * 
	 * @param list
	 * 		The list of IP adresses
	 */
	public void setIPs( Set<String> list ) {
		this._listIPs = list;
	}
	
	/**
	 * Gets the enum value that defines if the IPs in the set will be allowed or denied
	 * 
	 * @return the enum case (either ALLOW or BLOCK)
	 */
	public CASE getAllowedOrBlock( ) {
		return _caseAllowedOrBlock;
	}

	/**
	 * Sets the enum value that defines if the IPs in the set will be allowed or denied
	 * 
	 * @param AllowedOrBlock
	 * 		The enum value of allow or block
	 */
	public void setAllowedOrBlock( CASE AllowedOrBlock ) {
		this._caseAllowedOrBlock = AllowedOrBlock;
	}
	
	/**
	 * {@inheritDoc }
	 */
	@Override
	public boolean isRequestAuthenticated( HttpServletRequest request ) {
		if( _listIPs != null && _listIPs.contains( request.getRemoteAddr( ) ) )
		{
			return _caseAllowedOrBlock.getAuthorisation();
		}
		return !_caseAllowedOrBlock.getAuthorisation();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public AuthenticateRequestInformations getSecurityInformations( List<String> elements ) {
		
		return null;
	}


}
