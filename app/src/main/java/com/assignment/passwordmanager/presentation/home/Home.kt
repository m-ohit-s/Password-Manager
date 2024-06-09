package com.assignment.passwordmanager.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.passwordmanager.presentation.components.EditAccountForm
import com.assignment.passwordmanager.presentation.components.NewAccountForm
import com.assignment.passwordmanager.presentation.home.components.DataField
import com.assignment.passwordmanager.presentation.home.view_models.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
){
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {event->
            when(event){
                is HomeViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is HomeViewModel.UiEvent.SaveAccount -> {
                    viewModel.onEvent(HomeEvent.CloseDataSheet)
                }
            }
        }
    }
    Column(modifier = modifier) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Password Manager")
                },
                    colors = TopAppBarDefaults
                        .topAppBarColors(MaterialTheme.colorScheme.background)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(HomeEvent.OpenAddDataSheet)
                    },
                    containerColor = Color(0xFF3F7DE3),
                    contentColor = Color(0xFFFFFFFF),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(3.dp))
                LazyColumn(modifier = Modifier
                    .padding(25.dp)
                    .background(MaterialTheme.colorScheme.background)
                ) {
                    items(viewModel.state.value.accounts){
                        Spacer(modifier = Modifier.height(10.dp))
                        DataField(
                            modifier = Modifier.clickable {
                                viewModel.onEvent(HomeEvent.OpenEditDataSheet(account = it))
                            },
                            account = it
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    
                }
            }

            if(viewModel.state.value.isSheetOpen){
                ModalBottomSheet(
                    containerColor = Color(0xFFCBCBCB),
                    onDismissRequest = {
                        viewModel.onEvent(HomeEvent.CloseDataSheet)
                    },
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                    ) {
                        LoadModalForm(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun LoadModalForm(modifier: Modifier = Modifier, viewModel: HomeViewModel){
    if (viewModel.state.value.isAddData){
        NewAccountForm(modifier=modifier, viewModel=viewModel)
    }
    if(viewModel.state.value.isEditData){
        EditAccountForm(modifier=modifier, viewModel = viewModel)
    }
}