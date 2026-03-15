package com.example.chainsearch.initialAction.viewModels.states;

import java.util.List;

public record ExternalListener(
        boolean hasInternetConnection, List<String> perms, boolean hasStorage) {
    //empty
}
