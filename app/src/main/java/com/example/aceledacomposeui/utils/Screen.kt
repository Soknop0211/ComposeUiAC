package com.example.aceledacomposeui.utils

import com.example.aceledacomposeui.R

sealed class Screen(val title: String, val logo: Int) {
    object CountryLanguage : Screen("Country and Language", R.drawable.baseline_language_24)
    object Setting : Screen("Setting", R.drawable.baseline_settings_24)
    object Location : Screen("Locatoin", R.drawable.baseline_map_24)
    object TermsCondition :
        Screen("Terms and Condition", R.drawable.baseline_format_list_bulleted_24)

    object SecurityTip : Screen("Security Tips", R.drawable.baseline_security_24)
    object FAQs : Screen("FAQs", R.drawable.baseline_question_answer_24)
    object InviteFriends : Screen("Invite Friends", R.drawable.baseline_person_add_alt_1_24)
    object ACLEDABankStock : Screen("ACLEDA Bank Stock", R.drawable.baseline_auto_graph_24)
    object CustomerLoyalty : Screen("Customer Loyalty", R.drawable.baseline_loyalty_24)
    object CustomerUs : Screen("Contact Us(24/7)", R.drawable.baseline_local_phone_24)
    object Help : Screen("Help", R.drawable.baseline_help_24)
}
